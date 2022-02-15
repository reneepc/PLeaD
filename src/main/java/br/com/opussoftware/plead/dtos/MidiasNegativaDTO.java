package br.com.opussoftware.plead.dtos;

import br.com.opussoftware.plead.domain.enums.TipoSuspeita;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class MidiasNegativaDTO {
    @NotNull
    private TipoSuspeita suspeita;

    @NotEmpty
    @Size(min = 5, message = "A notícia deve ter no mínimo 5 caracteres")
    private String titulo;

    @Past(message = "A data de publicação da notícia deve ser anterior à atual")
    private Date dataPublicacao;

    @NotEmpty(message = "A notícia precisa de um URL fonte")
    @URL
    private String url;
}
