package br.com.opussoftware.plead.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@JsonTypeName("Pessoa Jurídica")
public class ProspectPJ extends Prospect {
    private String cnpj;
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
