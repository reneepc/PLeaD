package br.com.opussoftware.plead.dtos.mappers;

import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewProspectDTOMapper {
    @Mapping(target = "nomeRazaoSocial", source = "nome")
    ProspectPF toProspectPF(NewProspectDTO newProspectDTO);

    @Mapping(target = "nomeRazaoSocial", source = "razaoSocial")
    ProspectPJ toProspectPJ(NewProspectDTO newProspectDTO);
}
