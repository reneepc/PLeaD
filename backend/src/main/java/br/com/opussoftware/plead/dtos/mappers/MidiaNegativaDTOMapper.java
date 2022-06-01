package br.com.opussoftware.plead.dtos.mappers;

import br.com.opussoftware.plead.domain.MidiaNegativa;
import br.com.opussoftware.plead.dtos.inspecao.MidiaNegativaDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MidiaNegativaDTOMapper {
    MidiaNegativa toMidiaNegativa(MidiaNegativaDTO midiaNegativaDTO);
    Set<MidiaNegativa> toMidiaNegativa(Set<MidiaNegativaDTO> midiaNegativaDTO);
}
