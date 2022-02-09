package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.exceptions.ObjectNotFoundException;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectService {
    private final ProspectRepository repo;
    private final ModelMapper modelMapper;

    public ProspectService(ProspectRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    public Prospect findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Prospect.class));
    }

    public List<Prospect> findAll() {
        return repo.findAll();
    }

    public Prospect save(NewProspectDTO newProspectDTO) {
        Prospect newProspect;
        if(newProspectDTO.getTipo().equals("PF")) {
            newProspect = newProspectDTOToProspectPF(newProspectDTO);
        } else {
            newProspect = newProspectDTOToProspectPJ(newProspectDTO);
        }
        return repo.save(newProspect);
    }

    private boolean validPF(NewProspectDTO prospectDto) {
        if(prospectDto.getCpf() == null) {
            return false;
        }
        return prospectDto.getSobrenome() != null;
    }

    private boolean validPJ(NewProspectDTO newProspectDTO) {
        return newProspectDTO.getCnpj() != null;
    }

    private ProspectPJ newProspectDTOToProspectPJ(NewProspectDTO newProspectDTO) {
        if(validPJ(newProspectDTO)) {
            modelMapper.typeMap(NewProspectDTO.class, ProspectPJ.class)
                    .addMapping(NewProspectDTO::getRazaoSocial, Prospect::setNomeRazaoSocial);
            return modelMapper.map(newProspectDTO, ProspectPJ.class);
        }
        throw new IllegalArgumentException("O CNPJ é obrigatório para PJ");
    }

    private ProspectPF newProspectDTOToProspectPF(NewProspectDTO newProspectDTO) {
        if(validPF(newProspectDTO)) {
            modelMapper.typeMap(NewProspectDTO.class, ProspectPF.class)
                    .addMapping(NewProspectDTO::getNome, Prospect::setNomeRazaoSocial);
            return modelMapper.map(newProspectDTO, ProspectPF.class);
        }
        throw new IllegalArgumentException("O CPF e Sobrenome são obrigatórios para PF");
    }
}
