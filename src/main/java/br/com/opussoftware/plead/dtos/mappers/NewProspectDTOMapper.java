package br.com.opussoftware.plead.dtos.mappers;

import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewProspectDTOMapper {
    @Mapping(target = "nomeRazaoSocial", source = "nome")
    ProspectPF toProspectPF(NewProspectDTO newProspectDTO);

    @Mapping(target = "nomeRazaoSocial", source = "razaoSocial")
    ProspectPJ toProspectPJ(NewProspectDTO newProspectDTO);

    @BeforeMapping
    default void checkIsValidPF(NewProspectDTO newProspectDTO, @MappingTarget ProspectPF prospectPF) {
        if(newProspectDTO.getSobrenome() != null ||
                newProspectDTO.getNome() != null ||
                newProspectDTO.getCpf() != null) {
            throw new IllegalArgumentException("O CPF e Sobrenome são obrigatórios para PF");
        }
    }

    @BeforeMapping
    default void checkIsValidPJ(NewProspectDTO newProspectDTO, @MappingTarget ProspectPJ prospectPJ) {
        if(newProspectDTO.getCnpj() != null ||
                newProspectDTO.getRazaoSocial() != null) {
            throw new IllegalArgumentException("O CNPJ é obrigatório para PJ");
        }
    }
}
