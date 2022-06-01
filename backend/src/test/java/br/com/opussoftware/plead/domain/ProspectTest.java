package br.com.opussoftware.plead.domain;

import br.com.opussoftware.plead.domain.enums.StatusProspect;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProspectTest {
    @Test
    public void prospectInitiatedWithCorrectState() {
        Prospect prospectPF = new ProspectPF(null, "José", BigDecimal.valueOf(10000), false, "Inácio", "996.911.400-04"
                , null);
        Prospect prospectPJ = new ProspectPJ(null, "Coxinharia Dona Marta LTDA", BigDecimal.valueOf(46000), false,
                "59.170.690/0001-70", "Coxinharia da Dona Marta");

        assertEquals(StatusProspect.AGUARDANDO_PROCESSAMENTO, prospectPF.getStatus());
        assertEquals(StatusProspect.AGUARDANDO_PROCESSAMENTO, prospectPJ.getStatus());
    }
}
