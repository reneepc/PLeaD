package br.com.opussoftware.plead.dtos.inspecao;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InspecaoProspectPJDTO {
    @NotEmpty(message = "É necessário um id para que o prospect possa ser identificado unicamente")
    private Long id;

    @NotEmpty(message = "É necessário especificar uma razão social")
    @Size(min = 2, max = 100, message = "A razão social deve ter entre 2 e 100 caracteres")
    private String razaoSocial;

    @NotEmpty(message = "Uma pessoa jurídica deve ter um CNPJ")
    @CNPJ
    private String cnpj;

    @NotEmpty(message = "Uma pessoa jurídica deve conter um nome fantasia")
    private String nomeFantasia;

    public InspecaoProspectPJDTO(Long id, String razaoSocial, String cnpj, String nomeFantasia) {
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
