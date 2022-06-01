package br.com.opussoftware.plead.services;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPJ;
import br.com.opussoftware.plead.exceptions.ObjectNotFoundException;
import br.com.opussoftware.plead.repositories.ProspectPJRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProspectPJService {
    private final ProspectPJRepository repo;

    public ProspectPJService(ProspectPJRepository repo) {
        this.repo = repo;
    }

    public Page<Prospect> findAllByRazaoSocial(String razaoSocial, Pageable page) {
        return repo.findAllByRazaoSocial(razaoSocial, page);
    }

    public Prospect findByCnpj(String cnpj) {
        return repo.findByCnpj(cnpj).orElseThrow(() -> new ObjectNotFoundException(cnpj, ProspectPJ.class));
    }
}
