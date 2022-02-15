package br.com.opussoftware.plead.dtos.inspecao;

import br.com.opussoftware.plead.domain.enums.TipoSuspeita;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class MidiaNegativaDTO {
    @NotNull
    private TipoSuspeita suspeita;

    @NotEmpty
    @Size(min = 5, message = "A notícia deve ter no mínimo 5 caracteres")
    private String titulo;

    @Past(message = "A data de publicação da notícia deve ser anterior à atual")
    private Date dataPublicacao;

    @NotEmpty(message = "A notícia precisa de um URL fonte.")
    @URL
    private String Url;

    @NotEmpty(message = "É necessário relacionar a notícia ao id de um prospect")
    private Long id_prospect;

    public MidiaNegativaDTO(TipoSuspeita suspeita, String titulo, Date dataPublicacao, String url, Long id_prospect) {
        this.suspeita = suspeita;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        Url = url;
        this.id_prospect = id_prospect;
    }

    public TipoSuspeita getSuspeita() {
        return suspeita;
    }

    public void setSuspeita(TipoSuspeita suspeita) {
        this.suspeita = suspeita;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Long getId_prospect() {
        return id_prospect;
    }

    public void setId_prospect(Long id_prospect) {
        this.id_prospect = id_prospect;
    }
}
