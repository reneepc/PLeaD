package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.enums.TipoProcesso;
import br.com.opussoftware.plead.domain.enums.TipoSuspeita;
import br.com.opussoftware.plead.dtos.inspecao.ProcessoDTO;
import br.com.opussoftware.plead.dtos.inspecao.MidiaNegativaDTO;
import br.com.opussoftware.plead.dtos.inspecao.NewInspecaoDTO;
import br.com.opussoftware.plead.dtos.mappers.ProcessoDTOMapper;
import br.com.opussoftware.plead.dtos.mappers.ProcessoDTOMapperImpl;
import br.com.opussoftware.plead.services.ProspectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InpecaoControllerTest {
    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProspectService prospectService;

    @Test
    void inspectionPostAddsToUsers() throws Exception {
        Long id_prospect = 5L;
        Set<ProcessoDTO> processos = Stream.of(new ProcessoDTO(TipoProcesso.OUTROS,
                "Inquérito a respeito do destino de verba da licitação da linha 4",
                new Date(),
                "4234454563.524-2452",
                "https://jusbrasil.com.br/5734957"))
                .collect(Collectors.toSet());
        Set<MidiaNegativaDTO> midiaNegativas = Stream.of(new MidiaNegativaDTO(TipoSuspeita.CORRUPCAO,
                        "TJ-SP inicia investigação a cerca das obras da linha 4",
                        new Date(),
                        "https://globo.com/54353"))
                .collect(Collectors.toSet());

        NewInspecaoDTO newInspecaoDTO = new NewInspecaoDTO(id_prospect, processos, midiaNegativas);

        mock.perform(post("/inspecao")
                .contentType("application/json")
                .content(mapper.writeValueAsString(newInspecaoDTO)))
                .andExpect(status().isOk());

        Prospect prospect = prospectService.findById(id_prospect);

        assertFalse(prospect.getProcessos().isEmpty(), "O conjunto de processos não deveria ser vazio");
        assertFalse(prospect.getProcessos().isEmpty(), "O conjunto de midias negativas não deveria ser vazio");
    }

}
