package br.com.opussoftware.plead.dtos;

import br.com.opussoftware.plead.domain.enums.TipoProcesso;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessoDTO {
    private TipoProcesso tipo;

    @Size(min = 10, message = "A descrição deve conter no mínimo 10 caracteres")
    private String descricao;

    @Past(message = "A data informada deve ser no passado")
    private Date dataDeAbertura;

    @NotEmpty(message = "Todo processo deve ter um número CNJ válido")
    private String numeroCNJ;

    @NotEmpty(message = "É necessária uma fonte para o processo")
    @URL
    private String url;

    public ProcessoDTO(TipoProcesso tipo, String descricao, Date dataDeAbertura, String numeroCNJ, String url) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataDeAbertura = dataDeAbertura;
        this.numeroCNJ = numeroCNJ;
        this.url = url;
    }

    public TipoProcesso getTipo() {
        return tipo;
    }

    public void setTipo(TipoProcesso tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataDeAbertura() {
        return dataDeAbertura;
    }

    public void setDataDeAbertura(Date dataDeAbertura) {
        this.dataDeAbertura = dataDeAbertura;
    }

    public String getNumeroCNJ() {
        return numeroCNJ;
    }

    public void setNumeroCNJ(String numeroCNJ) {
        this.numeroCNJ = numeroCNJ;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
