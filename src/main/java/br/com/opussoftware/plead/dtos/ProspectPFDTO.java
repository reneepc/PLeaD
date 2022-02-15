package br.com.opussoftware.plead.dtos;

import br.com.opussoftware.plead.domain.JustificativaDeRecusa;
import br.com.opussoftware.plead.domain.enums.StatusProspect;
import br.com.opussoftware.plead.validators.NullableNotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProspectPFDTO {

    @NullableNotBlank
    private String nomePublico;

    @NotEmpty(message = "É necessário especificar um nome")
    @Size(min = 2, max = 200, message = "O nome deve ter entre 2 e 200 caracteres")
    private String nome;

    @CPF
    @NotEmpty(message = "Toda pessoa física precisa de um CPF")
    private String cpf;

    @PositiveOrZero(message = "O valor da renda mensal não pode ser negativo")
    private BigDecimal rendaMensal;

    private Boolean expostaPoliticamente;

    @NotNull
    private StatusProspect status;

    private Set<ProcessoDTO> processos;
    private Set<MidiasNegativaDTO> midiasNegativas;
    private Set<JustificativaDeRecusa> justificativas;

    public ProspectPFDTO(String nomePublico, String nome, String cpf, BigDecimal rendaMensal, Boolean expostaPoliticamente, StatusProspect status) {
        this.nomePublico = nomePublico;
        this.nome = nome;
        this.cpf = cpf;
        this.rendaMensal = rendaMensal;
        this.expostaPoliticamente = expostaPoliticamente;
        this.status = status;
    }

    public String getTipo() {
        return "PF";
    }

    public String getNomePublico() {
        return nomePublico;
    }

    public void setNomePublico(String nomePublico) {
        this.nomePublico = nomePublico;
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

    public BigDecimal getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(BigDecimal rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public Boolean getExpostaPoliticamente() {
        return expostaPoliticamente;
    }

    public void setExpostaPoliticamente(Boolean expostaPoliticamente) {
        this.expostaPoliticamente = expostaPoliticamente;
    }

    public StatusProspect getStatus() {
        return status;
    }

    public void setStatus(StatusProspect status) {
        this.status = status;
    }

    public Set<ProcessoDTO> getProcessos() {
        return processos;
    }

    public void setProcessos(Set<ProcessoDTO> processos) {
        this.processos = processos;
    }

    public Set<MidiasNegativaDTO> getMidiasNegativas() {
        return midiasNegativas;
    }

    public void setMidiasNegativas(Set<MidiasNegativaDTO> midiasNegativas) {
        this.midiasNegativas = midiasNegativas;
    }

    public Set<JustificativaDeRecusa> getJustificativas() {
        return justificativas;
    }

    public void setJustificativas(Set<JustificativaDeRecusa> justificativas) {
        this.justificativas = justificativas;
    }
}
