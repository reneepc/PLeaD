package br.com.opussoftware.plead.domain;

import br.com.opussoftware.plead.domain.enums.TipoJustificativa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "justificativa_de_recusa")
public class JustificativaDeRecusa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TipoJustificativa tipo;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "prospect_id")
    private Prospect prospect;
}