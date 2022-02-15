package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.services.ProspectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProspectController.class)
public class ProspectControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProspectService prospectService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void retrieveAllProspects() throws Exception {
        List<Prospect> prospects = new ArrayList<>();
        when(prospectService.findAll()).thenReturn(prospects);
        this.mvc.perform(get("/prospects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createNewProspectPJWithPOST() throws Exception {
        var prospectRequest = new NewProspectDTO("PJ", true, BigDecimal.valueOf(100000000),
                "89.208.072/0001-32", "Coca Cola Inc", "Coca Cola");
        var prospectResult = new ProspectPJ(1L, "Coca Cola Inc", BigDecimal.valueOf(10000000), true,
                "89.208.072/0001-32", "Coca Cola");
        when(prospectService.save(any(NewProspectDTO.class))).thenReturn(prospectResult);

        this.mvc.perform(post("/prospects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prospectRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/prospects/1"));

    }

    @Test
    public void rejectInvalidValuesForNewProspectType() throws Exception {
        var prospectShortProspectType = new NewProspectDTO("P", true, BigDecimal.valueOf(100000000),
                "89.208.072/0001-32", "Coca Cola Inc", "Coca Cola");
        var prospectLongProspectType = new NewProspectDTO("Contabil", true, BigDecimal.valueOf(100000000),
                "89.208.072/0001-32", "Coca Cola Inc", "Coca Cola");

        this.mvc.perform(post("/prospects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prospectShortProspectType)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);
                    assertTrue(result.getResolvedException().getMessage().contains("O tipo de prospect deve ser PF ou PJ"));
                });

        this.mvc.perform(post("/prospects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prospectLongProspectType)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);
                    assertTrue(result.getResolvedException().getMessage().contains("O tipo de prospect deve ser PF ou PJ"));
                });
    }

    @Test
    public void rejectInvalidValuesForNewProspectPF() throws Exception {
        var prospectInvalidCpf = new NewProspectDTO("PF", true, BigDecimal.valueOf(300000), "007.6547.828-72",
                "Paulo", "Salim Maluf", "Maluf");
        var prospectInvalidSobrenome = new NewProspectDTO("PF", true, BigDecimal.valueOf(300000), "007.687.828-72",
                "Paulo", "", "Maluf");

        this.mvc.perform(post("/prospects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prospectInvalidCpf)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> checkForValidationError(result, "CPF inválido"));

        this.mvc.perform(post("/prospects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prospectInvalidSobrenome)))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        checkForValidationError(result, "O sobrenome deve ter entre 2 e 150 caracteres"));
    }

    @Test
    public void rejectInvalidValuesForNewProspectPJ() throws Exception {
        var prospectInvalidCnpj = new NewProspectDTO("PJ", true, BigDecimal.valueOf(100000000),
                "89.208.07/0001-32", "Coca Cola Inc", "Coca Cola");
        var prospectInvalidNomeFantasia = new NewProspectDTO("PJ", true, BigDecimal.valueOf(100000000),
                "89.208.072/0001-32", "Coca Cola Inc", "Coca Cola-Coca Cola-Coca Cola-Coca " +
                "Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca " +
                "Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-Coca Cola-");

        this.mvc.perform(post("/prospects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prospectInvalidCnpj)))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        checkForValidationError(result, "CNPJ inválido"));

        this.mvc.perform(post("/prospects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prospectInvalidNomeFantasia)))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        checkForValidationError(result, "O nome fantasia deve ter entre 2 e 150 caracteres"));
    }


    private void checkForValidationError(MvcResult result, String errorMessage) {
        assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);
        assertTrue(result.getResolvedException().getMessage().contains(errorMessage));
    }
}
