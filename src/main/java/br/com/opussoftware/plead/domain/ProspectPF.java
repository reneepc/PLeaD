package br.com.opussoftware.plead.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@JsonTypeName("Pessoa Física")
public class ProspectPF extends Prospect {
    private String sobrenome;
    private String cpf;
    private String nomePublico;

    public ProspectPF() {}

    public ProspectPF(Long id, String nomeRazaoSocial, BigDecimal rendaAnual,
                      Boolean expostaPoliticamente, String sobrenome, String cpf, String nomePublico) {
        super(id, nomeRazaoSocial, rendaAnual, expostaPoliticamente);
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.nomePublico = nomePublico;
    }


}
