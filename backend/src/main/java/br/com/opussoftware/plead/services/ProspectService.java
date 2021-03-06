package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.enums.StatusProspect;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.dtos.mappers.NewProspectDTOMapper;
import br.com.opussoftware.plead.exceptions.ObjectNotFoundException;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public Page<Prospect> findAllByRendaMinima(BigDecimal rendaAnualMinima, Pageable page) {
        return repo.findAllByRendaAnualIsGreaterThan(rendaAnualMinima, page);
    }

    public Page<Prospect> findAll(Pageable page) {
        return repo.findAll(page);
    }

    public Prospect save(NewProspectDTO newProspectDTO) {
        Prospect newProspect = newProspectDTOMapper.toProspect(newProspectDTO);
        newProspect.setStatus(StatusProspect.AGUARDANDO_PROCESSAMENTO);
        return repo.save(newProspect);
    }

    public List<Prospect> saveAll(List<NewProspectDTO> newProspectDTOList) {
        List<Prospect> prospects = newProspectDTOMapper.toProspectList(newProspectDTOList);
        prospects.forEach(prospect -> prospect.setStatus(StatusProspect.AGUARDANDO_PROCESSAMENTO));
        return repo.saveAll(prospects);
    }
}
