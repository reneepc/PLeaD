package br.com.opussoftware.plead.repositories;

import br.com.opussoftware.plead.domain.MidiaNegativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidiaNegativaRepository extends JpaRepository<MidiaNegativa, Long> {
}
