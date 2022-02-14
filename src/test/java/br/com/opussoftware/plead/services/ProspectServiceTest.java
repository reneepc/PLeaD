package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProspectServiceTest {
    @MockBean
    private ProspectRepository prospectRepository;

    @InjectMocks
    private ProspectService prospectService;

    @Test
    public void saveWithInvalidValuesForProspectPFThrows() {
        var prospectNullCpf = new NewProspectDTO("PF", false, BigDecimal.valueOf(500000), null,
                null, "Everardo Oliveira Silva", "Tiririca");
        var prospectNullNome = new NewProspectDTO("PF", false, BigDecimal.valueOf(500000), "979.013.870-92",
                null, "Everardo Oliveira Silva", "Tiririca");
        var prospectNullSobrenome = new NewProspectDTO("PF", false, BigDecimal.valueOf(500000), "979.013.870-92",
                "Francisco", null, "Tiririca");

        assertThrows(IllegalArgumentException.class, () -> prospectService.save(prospectNullCpf),
                "CPF não poderia aceitar null para tipo = PF");
        assertThrows(IllegalArgumentException.class, () -> prospectService.save(prospectNullNome),
                "Nome não poderia aceitar null para tipo = PF");
        assertThrows(IllegalArgumentException.class, () -> prospectService.save(prospectNullSobrenome),
                "Sobrenome não poderia aceitar null para tipo = PF");
    }

    @Test
    public void saveWithInvalidValuesForProspectPJThrows() {
        var prospectNullCnpj = new NewProspectDTO("PJ", false, BigDecimal.valueOf(60000),
                null, "Bar do Zé", "Bar do Zé");
        var prospectNullRazaoSocial = new NewProspectDTO("PJ", false, BigDecimal.valueOf(60000), "039480238/334", null,
                "Bar do Zé");
        assertThrows(IllegalArgumentException.class, () -> prospectService.save(prospectNullCnpj),
                "CNPJ não poderia ser null para tipo = PJ");
        assertThrows(IllegalArgumentException.class, () -> prospectService.save(prospectNullRazaoSocial),
                "RazaoSocial não poderia ser null para tipo = PJ");
    }


}
