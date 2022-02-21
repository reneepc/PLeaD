package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.repositories.ProspectPFRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProspectPFService {
    private final ProspectPFRepository repo;


    public ProspectPFService(ProspectPFRepository repo) {
        this.repo = repo;
    }

    public Page<Prospect> findAllByNome(String nome, Pageable page) {
        return repo.findAllByNome(nome, page);
    }
}
