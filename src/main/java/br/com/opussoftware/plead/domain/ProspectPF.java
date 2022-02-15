package br.com.opussoftware.plead.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@JsonTypeName("PF")
public class ProspectPF extends Prospect {
    @NotEmpty(message = "Sobrenome é necessário para pessoa física")
    private String sobrenome;

    @NotEmpty(message = "Toda pessoa física precisa de um CPF")
    @CPF(message = "CPF inválido")
    @Column(unique = true)
    private String cpf;

    @Nullable
    private String nomePublico;

    public ProspectPF() {}

    public ProspectPF(Long id, String nomeRazaoSocial, BigDecimal rendaAnual,
                      Boolean expostaPoliticamente, String sobrenome, String cpf, String nomePublico) {
        super(id, nomeRazaoSocial, rendaAnual, expostaPoliticamente);
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.nomePublico = nomePublico;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Nullable
    public String getNomePublico() {
        return nomePublico;
    }

    public void setNomePublico(@Nullable String nomePublico) {
        this.nomePublico = nomePublico;
    }

}
