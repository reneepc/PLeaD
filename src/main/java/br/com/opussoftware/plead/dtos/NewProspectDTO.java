package br.com.opussoftware.plead.dtos;

import br.com.opussoftware.plead.validators.NullableNotBlank;
import br.com.opussoftware.plead.validators.ProspectType;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class NewProspectDTO {

    @ProspectType
    private String tipo;

    private Boolean expostaPoliticamente;

    @PositiveOrZero(message = "A renda deve ser positiva")
    private BigDecimal rendaAnual;

    /* Pessoa Física */
    @NullableNotBlank
    @CPF(message = "CPF inválido")
    private String cpf;

    @NullableNotBlank
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @NullableNotBlank
    @Size(min = 2, max = 150, message = "O sobrenome deve ter entre 2 e 150 caracteres")
    private String sobrenome;

    @NullableNotBlank
    @Size(min = 2, max = 100, message = "O nome público deve ter entre 2 e 100 caracteres")
    private String nomePublico;

    /* Pessoa Jurídica */
    @NullableNotBlank
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @NullableNotBlank
    @Size(min = 2, max = 150, message = "A razão social deve ter entre 2 e 250 caracteres")
    private String razaoSocial;

    @Size(min = 2, max = 150, message = "O nome fantasia deve ter entre 2 e 150 caracteres")
    private String nomeFantasia;

    public NewProspectDTO() {}

    public NewProspectDTO(String tipo, Boolean expostaPoliticamente, BigDecimal rendaAnual, String cpf, String nome,
                          String sobrenome, String nomePublico, String cnpj, String razaoSocial, String nomeFantasia) {
        this.tipo = tipo;
        this.expostaPoliticamente = expostaPoliticamente;
        this.rendaAnual = rendaAnual;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomePublico = nomePublico;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    /* Util para teste PJ*/
    public NewProspectDTO(String tipo, Boolean expostaPoliticamente, BigDecimal rendaAnual, String cnpj,
                          String razaoSocial, String nomeFantasia) {
        this.tipo = tipo;
        this.expostaPoliticamente = expostaPoliticamente;
        this.rendaAnual = rendaAnual;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    /* Util para teste PJ*/
    public NewProspectDTO(String tipo, Boolean expostaPoliticamente, BigDecimal rendaAnual, String cpf, String nome,
                          String sobrenome, String nomePublico) {
        this.tipo = tipo;
        this.expostaPoliticamente = expostaPoliticamente;
        this.rendaAnual = rendaAnual;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomePublico = nomePublico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
