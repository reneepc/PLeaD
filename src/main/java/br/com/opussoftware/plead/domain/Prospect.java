package br.com.opussoftware.plead.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Prospect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "É necessário especificar um nome ou razão social")
    @Size(min=2, max = 100, message = "O nome deve ter entra 2 e 100 caracteres")
    private String nomeRazaoSocial;

    @PositiveOrZero(message = "O valor da renda anual não pode ser negativo")
    private BigDecimal rendaAnual;

    private Boolean expostaPoliticamente;

    @ManyToMany
    @JoinTable(name = "prospect_processos",
            joinColumns = @JoinColumn(name = "prospect_id"),
            inverseJoinColumns = @JoinColumn(name = "processo_id"))
    private List<Processo> processos = new java.util.ArrayList<>();

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }

    public List<Processo> getProcessos() {
        return processos;
    }

    public Prospect() {}

    public Prospect(Long id, String nomeRazaoSocial, BigDecimal rendaAnual, Boolean expostaPoliticamente) {
        this.id = id;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.rendaAnual = rendaAnual;
        this.expostaPoliticamente = expostaPoliticamente;
    }

    public Prospect(Long id, String nomeRazaoSocial) {
        this.id = id;
        this.nomeRazaoSocial = nomeRazaoSocial;
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
}
