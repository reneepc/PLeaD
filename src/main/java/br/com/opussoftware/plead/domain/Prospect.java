package br.com.opussoftware.plead.domain;

import br.com.opussoftware.plead.domain.enums.StatusProspect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProspectPF.class, name = "PF"),
        @JsonSubTypes.Type(value = ProspectPJ.class, name = "PJ")
})
@Getter
@Setter
@NoArgsConstructor
public abstract class Prospect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "É necessário especificar um nome ou razão social")
    @Size(min=2, max = 100, message = "O nome deve ter entra 2 e 100 caracteres")
    private String nomeRazaoSocial;

    @PositiveOrZero(message = "O valor da renda anual não pode ser negativo")
    @Nullable
    private BigDecimal rendaAnual;

    @Nullable
    private Boolean expostaPoliticamente;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusProspect status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "processos_envolvendo_prospect",
            joinColumns = @JoinColumn(name = "prospect_id"),
            inverseJoinColumns = @JoinColumn(name = "processo_id"))
    private Set<Processo> processos = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "midia_negativa_envolvendo_prospect",
            joinColumns = @JoinColumn(name = "prospect_id"),
            inverseJoinColumns = @JoinColumn(name = "midia_id"))
    private Set<MidiaNegativa> midiasNegativas = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "prospect")
    Set<JustificativaDeRecusa> justificativas = new java.util.LinkedHashSet<>();

    public Prospect(Long id, String nomeRazaoSocial, BigDecimal rendaAnual, Boolean expostaPoliticamente) {
        this.id = id;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.rendaAnual = rendaAnual;
        this.expostaPoliticamente = expostaPoliticamente;
        this.status = StatusProspect.AGUARDANDO_PROCESSAMENTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prospect prospect = (Prospect) o;
        return Objects.equals(id, prospect.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Processo associateProcesso(Processo processo) {
        processo.getProspects().add(this);
        processos.add(processo);
        return processo;
    }

    public MidiaNegativa associateMidiaNegativa(MidiaNegativa midiaNegativa) {
        midiaNegativa.getProspects().add(this);
        midiasNegativas.add(midiaNegativa);
        return midiaNegativa;
    }
}