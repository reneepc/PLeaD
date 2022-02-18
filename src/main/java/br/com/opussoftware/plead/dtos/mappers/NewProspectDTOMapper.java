package br.com.opussoftware.plead.dtos.mappers;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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

    @BeforeMapping
    private void checkIsValidPF(NewProspectDTO newProspectDTO, @MappingTarget ProspectPF prospectPF) {
        if(newProspectDTO.getSobrenome() == null ||
                newProspectDTO.getNome() == null ||
                newProspectDTO.getCpf() == null) {
            throw new IllegalArgumentException("O CPF e Sobrenome são obrigatórios para PF");
        }
    }

    @BeforeMapping
    private void checkIsValidPJ(NewProspectDTO newProspectDTO, @MappingTarget ProspectPJ prospectPJ) {
        if(newProspectDTO.getCnpj() == null ||
                newProspectDTO.getRazaoSocial() == null) {
            throw new IllegalArgumentException("O CNPJ é obrigatório para PJ");
        }
    }
}
