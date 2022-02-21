package br.com.opussoftware.plead.repositories;

import br.com.opussoftware.plead.domain.Prospect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProspectRepository extends JpaRepository<Prospect, Long> {
    @Query("select p from Prospect p" +
            " join ProspectPF pf on pf.id = p.id" +
            " where LOWER(p.nomeRazaoSocial) like lower(concat('%', :nome, '%'))")
    Page<Prospect> findAllByNome(String nome, Pageable pageable);

    @Query("select p from Prospect p" +
            " join ProspectPJ pj on pj.id = p.id" +
            " where LOWER(p.nomeRazaoSocial) like lower(concat('%', :razaoSocial, '%'))")
    Page<Prospect> findAllByRazaoSocial(String razaoSocial, Pageable pageable);

    Page<Prospect> findAllByRendaAnualIsGreaterThan(BigDecimal rendaAnualMinima, Pageable pageable);
}
