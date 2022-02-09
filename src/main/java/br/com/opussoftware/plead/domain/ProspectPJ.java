package br.com.opussoftware.plead.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@JsonTypeName("Pessoa Jurídica")
public class ProspectPJ extends Prospect {
    @NotEmpty(message = "Uma pessoa jurídica deve conter um CNPJ")
    @CNPJ(message = "CNPJ inválido")
    @Column(unique = true)
    private String cnpj;

    @NotEmpty(message = "Uma pessoa jurídica deve conter um nome fantasia")
    private String nomeFantasia;

    public ProspectPJ() {}

    public ProspectPJ(Long id, String nomeRazaoSocial, BigDecimal rendaAnual,
                      Boolean expostaPoliticamente, String cnpj, String nomeFantasia) {
        super(id, nomeRazaoSocial, rendaAnual, expostaPoliticamente);
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
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
