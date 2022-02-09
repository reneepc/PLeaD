package br.com.opussoftware.plead;

import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class newProspectDTO {
    @Size(min = 2, max = 2, message = "O tipo de prospect deve ser PF ou PJ")
    private String tipo;

    @CPF(message = "CPF inválido")
    private String cpf;

    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @NotEmpty
    //@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @NotEmpty
    @Size(min = 2, max = 100, message = "O sobrenome deve ter entre 2 e 50 caracteres")
    private String sobrenome;

    @NotEmpty
    @Size(min = 2, max = 100, message = "O sobrenome deve ter entre 2 e 50 caracteres")
    private String nomePublico;

    private Boolean expostaPoliticamente;

    @PositiveOrZero(message = "A renda deve ser positiva")
    private BigDecimal rendaAnual;

    public newProspectDTO() {}

    public newProspectDTO(String tipo, String cpf, String cnpj, String nome, String sobrenome, String nomePublico,
                          Boolean expostaPoliticamente, BigDecimal rendaAnual) {
        this.tipo = tipo;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomePublico = nomePublico;
        this.expostaPoliticamente = expostaPoliticamente;
        this.rendaAnual = rendaAnual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomePublico() {
        return nomePublico;
    }

    public void setNomePublico(String nomePublico) {
        this.nomePublico = nomePublico;
    }

    public Boolean getExpostaPoliticamente() {
        return expostaPoliticamente;
    }

    public void setExpostaPoliticamente(Boolean expostaPoliticamente) {
        this.expostaPoliticamente = expostaPoliticamente;
    }

    public BigDecimal getRendaAnual() {
        return rendaAnual;
    }

    public void setRendaAnual(BigDecimal rendaAnual) {
        this.rendaAnual = rendaAnual;
    }
}
