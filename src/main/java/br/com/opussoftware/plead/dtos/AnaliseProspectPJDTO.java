package br.com.opussoftware.plead.dtos;

import br.com.opussoftware.plead.validators.ProspectType;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AnaliseProspectPJDTO {
    @NotEmpty(message = "É necessário um id para que o prospect possa ser identificado unicamente")
    private Long id;

    @NotEmpty(message = "É necessário especificar uma razão social")
    @Size(min = 2, max = 200, message = "A razão social deve ter entre 2 e 200 caracteres")
    private String razaoSocial;

    @NotEmpty(message = "Uma pessoa jurídica deve ter um CNPJ")
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @NotEmpty(message = "Uma pessoa jurídica deve conter um nome fantasia")
    private String nomeFantasia;

    public AnaliseProspectPJDTO(Long id, String razaoSocial, String cnpj, String nomeFantasia) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
    }

    public String getTipo() {
        return "PJ";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
