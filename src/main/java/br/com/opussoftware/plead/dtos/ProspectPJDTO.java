package br.com.opussoftware.plead.dtos;

import br.com.opussoftware.plead.domain.JustificativaDeRecusa;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProspectPJDTO {
    @NotEmpty(message = "É necessário especificar uma razão social")
    @Size(min=2, max = 100, message = "A razão social deve ter entre 2 e 100 caracteres")
    private String razaoSocial;

    @NotEmpty(message = "Uma pessoa jurídica deve conter um nome fantasia")
    private String nomeFantasia;

    @NotEmpty(message = "Uma pessoa jurídica deve conter um CNPJ")
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @PositiveOrZero(message = "O valor da renda anual não pode ser negativo")
    private BigDecimal rendaAnual;

    private Set<ProcessoDTO> processos;
    private Set<MidiasNegativaDTO> midiasNegativas;
    private Set<JustificativaDeRecusa> justificativas;

    public ProspectPJDTO(String razaoSocial, String nomeFantasia, String cnpj, BigDecimal rendaAnual) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.rendaAnual = rendaAnual;
    }

    public String getTipo() {
        return "PJ";
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BigDecimal getRendaAnual() {
        return rendaAnual;
    }

    public void setRendaAnual(BigDecimal rendaAnual) {
        this.rendaAnual = rendaAnual;
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
