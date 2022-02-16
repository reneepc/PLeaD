package br.com.opussoftware.plead.domain;

import br.com.opussoftware.plead.domain.enums.StatusProspect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sun.istack.Nullable;

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

    @ManyToMany
    @JoinTable(name = "processos_envolvendo_prospect",
            joinColumns = @JoinColumn(name = "prospect_id"),
            inverseJoinColumns = @JoinColumn(name = "processo_id"))
    private Set<Processo> processos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "midia_negativa_envolvendo_prospect",
            joinColumns = @JoinColumn(name = "prospect_id"),
            inverseJoinColumns = @JoinColumn(name = "midia_id"))
    private Set<MidiaNegativa> midiasNegativas = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "prospect")
    Set<JustificativaDeRecusa> justificativas = new java.util.LinkedHashSet<>();

    public Prospect() {}

    public Prospect(Long id, String nomeRazaoSocial, BigDecimal rendaAnual, Boolean expostaPoliticamente) {
        this.id = id;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.rendaAnual = rendaAnual;
        this.expostaPoliticamente = expostaPoliticamente;
        this.status = StatusProspect.AGUARDANDO_PROCESSAMENTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public BigDecimal getRendaAnual() {
        return rendaAnual;
    }

    public void setRendaAnual(BigDecimal rendaAnual) {
        this.rendaAnual = rendaAnual;
    }

    public Boolean getExpostaPoliticamente() {
        return expostaPoliticamente;
    }

    public void setExpostaPoliticamente(Boolean expostaPoliticamente) {
        this.expostaPoliticamente = expostaPoliticamente;
    }

    public Set<MidiaNegativa> getMidiasNegativas() {
        return midiasNegativas;
    }

    public void setMidiasNegativas(Set<MidiaNegativa> midiasNegativas) {
        this.midiasNegativas = midiasNegativas;
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

    public StatusProspect getStatus() {
        return status;
    }

    public void setStatus(StatusProspect status) {
        this.status = status;
    }


    public void setProcessos(Set<Processo> processos) {
        this.processos = processos;
    }

    public Set<Processo> getProcessos() {
        return processos;
    }

    public void setJustificativas(Set<JustificativaDeRecusa> justificativas) {
        this.justificativas = justificativas;
    }

    public Set<JustificativaDeRecusa> getJustificativas() {
        return justificativas;
    }
}