package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.exceptions.ObjectNotFoundException;
import br.com.opussoftware.plead.repositories.ProspectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectService {
    private final ProspectRepository repo;

    public ProspectService(ProspectRepository repo) {
        this.repo = repo;
    }

    public Prospect findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Prospect.class));
    }


    public List<Prospect> findAll() {
        return repo.findAll();
    }
}
