package br.com.opussoftware.plead.domain;

import br.com.opussoftware.plead.domain.enums.TipoSuspeita;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.ArrayList;
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
    @Min(value = 5, message = "A notícia deve ter no mínimo 5 caracteres")
    private String titulo;

    @NotEmpty(message = "A notícia precisa de um URL fonte.")
    private String Url;

    @Past(message = "A data de publicação da notícia deve ser anterior à atual")
    private Date dataPublicacao;

    private TipoSuspeita suspeita;

    @ManyToMany(mappedBy = "midiasNegativas")
    private Set<Prospect> prospects = new HashSet<>();

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
}
