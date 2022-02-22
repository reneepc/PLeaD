package br.com.opussoftware.plead.dtos.mappers;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NewProspectDTOMapper {

    @Named(value = "prospectMapper")
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

    @IterableMapping(qualifiedByName = "prospectMapper")
    public abstract List<Prospect> toProspectList(List<NewProspectDTO> newProspectDTOList);
}
