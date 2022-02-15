package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.dtos.ProspectPFDTO;
import br.com.opussoftware.plead.dtos.ProspectPJDTO;
import br.com.opussoftware.plead.exceptions.ObjectNotFoundException;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        return prospectDto.getSobrenome() != null &&
                prospectDto.getNome() != null &&
                prospectDto.getCpf() != null;
    }

    private boolean validPJ(NewProspectDTO newProspectDTO) {
        return newProspectDTO.getCnpj() != null &&
                newProspectDTO.getRazaoSocial() != null;
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

    private ProspectPFDTO ProspectPFToProspectPFDTO(ProspectPF prospectPF) {
        modelMapper.typeMap(ProspectPF.class, ProspectPFDTO.class)
                .addMapping((src) -> src.getNomeRazaoSocial() + src.getSobrenome(), ProspectPFDTO::setNome)
                .addMapping((src) -> src.getRendaAnual().divide(BigDecimal.valueOf(12)),
                        ProspectPFDTO::setRendaMensal)
                .addMapping((src) -> src.getProcessos().isEmpty() ? null : src.getProcessos(),
                        ProspectPFDTO::setProcessos)
                .addMapping((src) -> src.getMidiasNegativas().isEmpty() ? null : src.getMidiasNegativas(),
                        ProspectPFDTO::setMidiasNegativas)
                .addMapping((src) -> src.getJustificativas().isEmpty() ? null : src.getJustificativas(),
                        ProspectPFDTO::setJustificativas);

        return modelMapper.map(prospectPF, ProspectPFDTO.class);
    }

    private ProspectPJDTO ProspectPJToProspectPJDTO(ProspectPJ prospectPJ) {
        modelMapper.typeMap(ProspectPJ.class, ProspectPJDTO.class)
                .addMapping(ProspectPJ::getNomeRazaoSocial, ProspectPJDTO::setRazaoSocial)
                .addMapping((src) -> src.getProcessos().isEmpty() ? null : src.getProcessos(),
                        ProspectPJDTO::setProcessos)
                .addMapping((src) -> src.getMidiasNegativas().isEmpty() ? null : src.getMidiasNegativas(),
                        ProspectPJDTO::setMidiasNegativas)
                .addMapping((src) -> src.getJustificativas().isEmpty() ? null : src.getJustificativas(),
                        ProspectPJDTO::setJustificativas);
        return modelMapper.map(prospectPJ, ProspectPJDTO.class);
    }
}
