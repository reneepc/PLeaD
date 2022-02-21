package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import br.com.opussoftware.plead.exceptions.ObjectNotFoundException;
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

    public Prospect findByCpf(String cpf) {
        return repo.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException(cpf, ProspectPF.class));
    }
}
