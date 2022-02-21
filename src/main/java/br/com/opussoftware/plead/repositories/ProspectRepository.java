package br.com.opussoftware.plead.repositories;

import br.com.opussoftware.plead.domain.Prospect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProspectRepository extends JpaRepository<Prospect, Long> {
    Page<Prospect> findAllByRendaAnualIsGreaterThan(BigDecimal rendaAnualMinima, Pageable pageable);
}
