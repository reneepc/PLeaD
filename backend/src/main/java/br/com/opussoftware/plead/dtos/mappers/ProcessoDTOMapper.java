package br.com.opussoftware.plead.dtos.mappers;

import br.com.opussoftware.plead.domain.Processo;
import br.com.opussoftware.plead.dtos.inspecao.ProcessoDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProcessoDTOMapper {
    Processo toProcesso(ProcessoDTO processoDTO);

    Set<Processo> toProcesso(Set<ProcessoDTO> processoDTO);
}
