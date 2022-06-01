package br.com.opussoftware.plead.config;

import br.com.opussoftware.plead.domain.MidiaNegativa;
import br.com.opussoftware.plead.domain.Processo;
import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.domain.enums.StatusProspect;
import br.com.opussoftware.plead.domain.enums.TipoProcesso;
import br.com.opussoftware.plead.domain.enums.TipoSuspeita;
import br.com.opussoftware.plead.repositories.MidiaNegativaRepository;
import br.com.opussoftware.plead.repositories.ProcessoRepository;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class MockDB implements CommandLineRunner {
    private final ProspectRepository prospectRepository;
    private final ProcessoRepository processoRepository;
    private final MidiaNegativaRepository midiaNegativaRepository;

    public MockDB(ProspectRepository prospectRepository, ProcessoRepository processoRepository,
                  MidiaNegativaRepository midiaNegativaRepository) {
        this.prospectRepository = prospectRepository;
        this.processoRepository = processoRepository;
        this.midiaNegativaRepository = midiaNegativaRepository;
    }

    private void makeDatabaseInstance() {
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

        Processo proc1 = new Processo(null, new Date(), TipoProcesso.BUSCA_E_APREENCAO, "12354556",
                "Processo de busca e apreenção aos suspeitos da operação Lava Jato",
                "https://jusbrasil.com.br/084302");
        Processo proc2 = new Processo(null, new Date(), TipoProcesso.EXECUCAO_FISCAL, "12354556",
                "Execução fiscal da prefeitura de são paulo",
                "https://jusbrasil.com.br/039482048");

        p1.associateProcesso(proc1);
        p1.associateProcesso(proc2);
        p2.associateProcesso(proc2);
        p3.associateProcesso(proc1);
        p6.associateProcesso(proc1);

        MidiaNegativa midia1 = new MidiaNegativa(null, "Polícia Federal apreende bens de 10 pessoas suspeitas " +
                "de colaboração na operação Lava Jato", "https://globo.com.br/2384509278", new Date(),
                TipoSuspeita.CORRUPCAO);

        p1.associateMidiaNegativa(midia1);
        p3.associateMidiaNegativa(midia1);
        p6.associateMidiaNegativa(midia1);

        p1.setStatus(StatusProspect.REPROVADO);
        p2.setStatus(StatusProspect.APROVADO);
        p3.setStatus(StatusProspect.REPROVADO);
        p6.setStatus(StatusProspect.REPROVADO);


        processoRepository.saveAll(List.of(proc1, proc2));
        midiaNegativaRepository.saveAll(List.of(midia1));
        prospectRepository.saveAll(List.of(p1,p2,p3,p4,p5,p6));
    }

    @Override
    public void run(String... args) throws Exception {
        makeDatabaseInstance();
    }
}
