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
                "Borges", "714.468.890-17", "Mc quim");
        Prospect p2 = new ProspectPJ(null, "Bar do Bigode LTDA", BigDecimal.valueOf(700000), true,
                "67.571.760/0001-20", "Bigode's Bar");
        Prospect p3 = new ProspectPF(null, "Abigail", BigDecimal.valueOf(40000.00), false,
                "Bruxelas", "672.181.210-04", null);
        Prospect p4 = new ProspectPF(null, "Jeremias", BigDecimal.valueOf(100000.00), true,
                "Carvalho Almeida", "450.783.720-08", "DJ do Gado");
        Prospect p5 = new ProspectPJ(null, "Cocócoral Cocoricó SA", BigDecimal.valueOf(1000000), true,
                "84.242.492/0001-11", "Cocócoral");
        Prospect p6 = new ProspectPF(null,  "Dona", BigDecimal.valueOf(10000000000000.00), true,
                "Rose", "577.537.470-37", "Matriarca");

        prospectRepository.saveAll(List.of(p1,p2,p3,p4,p5,p6));
    }
}
