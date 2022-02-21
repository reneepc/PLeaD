package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.MidiaNegativa;
import br.com.opussoftware.plead.domain.Processo;
import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.enums.StatusProspect;
import br.com.opussoftware.plead.dtos.inspecao.NewInspecaoDTO;
import br.com.opussoftware.plead.dtos.inspecao.ProcessoDTO;
import br.com.opussoftware.plead.dtos.mappers.MidiaNegativaDTOMapper;
import br.com.opussoftware.plead.dtos.mappers.ProcessoDTOMapper;
import br.com.opussoftware.plead.exceptions.ObjectNotFoundException;
import br.com.opussoftware.plead.repositories.MidiaNegativaRepository;
import br.com.opussoftware.plead.repositories.ProcessoRepository;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InspecaoService {
    private final ProspectRepository prospectRepository;
    private final ProcessoRepository processoRepository;
    private final MidiaNegativaRepository midiaNegativaRepository;
    private final ProcessoDTOMapper processoDTOMapper;
    private final MidiaNegativaDTOMapper midiaNegativaDTOMapper;

    public InspecaoService(ProspectRepository prospectRepository,
                           ProcessoRepository processoRepository,
                           MidiaNegativaRepository midiaNegativaRepository,
                           ProcessoDTOMapper processoDTOMapper,
                           MidiaNegativaDTOMapper midiaNegativaDTOMapper) {
        this.prospectRepository = prospectRepository;
        this.processoRepository = processoRepository;
        this.midiaNegativaRepository = midiaNegativaRepository;
        this.processoDTOMapper = processoDTOMapper;
        this.midiaNegativaDTOMapper = midiaNegativaDTOMapper;
    }

    @Transactional
    public void save(NewInspecaoDTO newInspecaoDTO) {
        Long id = newInspecaoDTO.getId_prospect();
        Prospect prospect = prospectRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Prospect.class));
        Set<Processo> processos = processoDTOMapper.toProcesso(newInspecaoDTO.getProcessos());
        processos = processos.stream().map(prospect::associateProcesso).collect(Collectors.toSet());
        Set<MidiaNegativa> midiaNegativas = midiaNegativaDTOMapper.toMidiaNegativa(newInspecaoDTO.getMidiasNegativas());
        midiaNegativas = midiaNegativas.stream().map(prospect::associateMidiaNegativa).collect(Collectors.toSet());
        prospect.setStatus(StatusProspect.EM_ANALISE);

        processoRepository.saveAll(processos);
        midiaNegativaRepository.saveAll(midiaNegativas);
        prospectRepository.save(prospect);
    }
}
