package br.com.opussoftware.plead.repositories;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPJ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProspectPJRepository extends JpaRepository<ProspectPJ, Long> {
    @Query("select p from ProspectPJ p where LOWER(p.nomeRazaoSocial) like lower(concat('%', :razaoSocial, '%'))")
    Page<Prospect> findAllByRazaoSocial(String razaoSocial, Pageable pageable);

    Optional<Prospect> findByCnpj(String cnpj);
}
