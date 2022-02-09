package br.com.opussoftware.plead.repositories;

import br.com.opussoftware.plead.domain.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectRepository extends JpaRepository<Prospect, Long> {
}
