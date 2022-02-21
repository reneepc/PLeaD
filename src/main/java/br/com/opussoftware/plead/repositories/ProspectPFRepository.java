package br.com.opussoftware.plead.repositories;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.domain.ProspectPF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectPFRepository extends JpaRepository<ProspectPF, Long> {
    @Query("select p from ProspectPF p where LOWER(p.nomeRazaoSocial) like lower(concat('%', :nome, '%'))")
    Page<Prospect> findAllByNome(String nome, Pageable pageable);
}
