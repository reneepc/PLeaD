package br.com.opussoftware.plead.dtos.analysis;

import br.com.opussoftware.plead.validators.NullableNotBlank;
import br.com.opussoftware.plead.validators.ProspectType;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AnaliseProspectPFDTO {
    @NotEmpty(message = "É necessário um id para que o prospect possa ser identificado unicamente.")
    private Long id;

    @NotEmpty(message = "É necessário especificar um nome")
    @Size(min = 2, max = 100, message = "O Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotEmpty(message = "Toda pessoa física precisa de um CPF")
    @CPF
    private String cpf;

    @NullableNotBlank
    private String nomePublico;

    public AnaliseProspectPFDTO(Long id, String nome, String cpf, String nomePublico) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.nomePublico = nomePublico;
    }

    public String getTipo() {
        return "PF";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomePublico() {
        return nomePublico;
    }

    public void setNomePublico(String nomePublico) {
        this.nomePublico = nomePublico;
    }
}
