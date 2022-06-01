package br.com.opussoftware.plead.dtos.inspecao;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class NewInspecaoDTO {
    @NotNull(message = "É necessário relacionar um processo ao id de um prospect")
    Long id_prospect;

    @NotNull(message = "O campo processos não pode ser nulo")
    private Set<ProcessoDTO> processos;

    @NotNull(message = "O campo midiasNegativas não pode ser nulo")
    private Set<MidiaNegativaDTO> midiasNegativas;

    public NewInspecaoDTO() {}

    public NewInspecaoDTO(Long id_prospect, Set<ProcessoDTO> processos, Set<MidiaNegativaDTO> midiasNegativas) {
        this.id_prospect = id_prospect;
        this.processos = processos;
        this.midiasNegativas = midiasNegativas;
    }

    public Long getId_prospect() {
        return id_prospect;
    }

    public void setId_prospect(Long id_prospect) {
        this.id_prospect = id_prospect;
    }

    public Set<ProcessoDTO> getProcessos() {
        return processos;
    }

    public void setProcessos(Set<ProcessoDTO> processos) {
        this.processos = processos;
    }

    public Set<MidiaNegativaDTO> getMidiasNegativas() {
        return midiasNegativas;
    }

    public void setMidiasNegativas(Set<MidiaNegativaDTO> midiasNegativas) {
        this.midiasNegativas = midiasNegativas;
    }

}
