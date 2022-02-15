package br.com.opussoftware.plead.dtos.inspecao;

import br.com.opussoftware.plead.dtos.ProcessoDTO;

import java.util.Set;

public class NewInspecaoDTO {
    private Set<ProcessoDTO> processos;
    private Set<MidiaNegativaDTO> midiasNegativas;

    public NewInspecaoDTO(Set<ProcessoDTO> processos, Set<MidiaNegativaDTO> midiasNegativas) {
        this.processos = processos;
        this.midiasNegativas = midiasNegativas;
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
