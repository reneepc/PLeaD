package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MockDB {
    private final ProspectRepository prospectRepository;

    public MockDB(ProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    @Bean
    public void makeDatabaseInstance() {
        Prospect p1 = new ProspectPF(null, "Joaquim", BigDecimal.valueOf(50000.00), false,
                "Borges", "06507908", "Mc" + " quim");
        Prospect p2 = new ProspectPJ(null, "Bar do Bigode LTDA", BigDecimal.valueOf(700000), true,
                "4598347502405782", "Bigode's Bar");

        prospectRepository.saveAll(List.of(p1,p2));
    }
}
