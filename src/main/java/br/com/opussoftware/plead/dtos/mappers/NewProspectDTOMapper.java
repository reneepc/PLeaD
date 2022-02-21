package br.com.opussoftware.plead.dtos.mappers;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class NewProspectDTOMapper {

    public Prospect toProspect(NewProspectDTO newProspectDTO) {
        if(newProspectDTO.getTipo().equals("PF")) {
            return toProspectPF(newProspectDTO);
        } else {
            return toProspectPJ(newProspectDTO);
        }
    }
    @Mapping(target = "nomeRazaoSocial", source = "nome")
    public abstract ProspectPF toProspectPF(NewProspectDTO newProspectDTO);

    @Mapping(target = "nomeRazaoSocial", source = "razaoSocial")
    public abstract ProspectPJ toProspectPJ(NewProspectDTO newProspectDTO);
}
