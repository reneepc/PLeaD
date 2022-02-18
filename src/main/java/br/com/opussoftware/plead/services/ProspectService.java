package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.enums.StatusProspect;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.dtos.mappers.NewProspectDTOMapper;
import br.com.opussoftware.plead.exceptions.ObjectNotFoundException;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectService {
    private final ProspectRepository repo;
    private final NewProspectDTOMapper newProspectDTOMapper;

    public ProspectService(ProspectRepository repo, NewProspectDTOMapper newProspectDTOMapper) {
        this.repo = repo;
        this.newProspectDTOMapper = newProspectDTOMapper;
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
            newProspect = newProspectDTOMapper.toProspectPF(newProspectDTO);
        } else {
            newProspect = newProspectDTOMapper.toProspectPJ(newProspectDTO);
        }
        newProspect.setStatus(StatusProspect.AGUARDANDO_PROCESSAMENTO);
        return repo.save(newProspect);
    }

}
