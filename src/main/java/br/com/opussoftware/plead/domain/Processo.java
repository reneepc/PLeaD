package br.com.opussoftware.plead.domain;

import br.com.opussoftware.plead.domain.enums.TipoProcesso;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Past(message = "A data informada deve ser no passado")
    private Date dataDeAbertura;

    private TipoProcesso tipo;

    @NotEmpty(message = "Todo processo deve ter um número CNJ válido")
    private String numeroCNJ;

    @Size(min = 10, message = "A descrição deve conter no mínimo 10 caracteres")
    private String descricao;

    @NotEmpty(message = "É necessária uma fonte para o processo")
    @URL
    private String url;

    @JsonIgnore
    @ManyToMany(mappedBy = "processos")
    private Set<Prospect> prospects = new HashSet<>();

    public Processo() {}

    public Processo(Long id, Date dataDeAbertura, TipoProcesso tipo, String numeroCNJ, String descricao, String url) {
        this.id = id;
        this.dataDeAbertura = dataDeAbertura;
        this.tipo = tipo;
        this.numeroCNJ = numeroCNJ;
        this.descricao = descricao;
        this.url = url;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDeAbertura() {
        return dataDeAbertura;
    }

    public void setDataDeAbertura(Date dataDeAbertura) {
        this.dataDeAbertura = dataDeAbertura;
    }

    public TipoProcesso getTipo() {
        return tipo;
    }

    public void setTipo(TipoProcesso tipo) {
        this.tipo = tipo;
    }

    public String getNumeroCNJ() {
        return numeroCNJ;
    }

    public void setNumeroCNJ(String numeroCNJ) {
        this.numeroCNJ = numeroCNJ;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Prospect> getProspects() {
        return prospects;
    }

    public void setProspects(Set<Prospect> prospects) {
        this.prospects = prospects;
    }
}