package br.com.opussoftware.plead.domain;

import br.com.opussoftware.plead.domain.enums.TipoSuspeita;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class MidiaNegativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 5, message = "A notícia deve ter no mínimo 5 caracteres")
    private String titulo;

    @NotEmpty(message = "A notícia precisa de um URL fonte.")
    private String Url;

    @Past(message = "A data de publicação da notícia deve ser anterior à atual")
    private Date dataPublicacao;

    @NotNull
    private TipoSuspeita suspeita;

    @JsonIgnore
    @ManyToMany(mappedBy = "midiasNegativas")
    private Set<Prospect> prospects = new HashSet<>();

    public MidiaNegativa() {}

    public MidiaNegativa(Long id, String titulo, String url, Date dataPublicacao, TipoSuspeita suspeita) {
        this.id = id;
        this.titulo = titulo;
        Url = url;
        this.dataPublicacao = dataPublicacao;
        this.suspeita = suspeita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public TipoSuspeita getSuspeita() {
        return suspeita;
    }

    public void setSuspeita(TipoSuspeita suspeita) {
        this.suspeita = suspeita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MidiaNegativa that = (MidiaNegativa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Set<Prospect> getProspects() {
        return prospects;
    }

    public void setProspects(Set<Prospect> prospects) {
        this.prospects = prospects;
    }
}
