package br.com.opussoftware.plead.domain;

import br.com.opussoftware.plead.domain.enums.TipoProcesso;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Past(message = "A data informada deve ser anterior à atual")
    private Date dataDeAbertura;

    @Enumerated(value = EnumType.STRING)
    private TipoProcesso tipo;

    @NotEmpty(message = "Todo processo deve ter um número CNJ válido")
    private String numeroCNJ;

    @Size(min = 10, message = "A descrição deve conter no mínimo 10 caracteres")
    private String descricao;

    @NotEmpty(message = "É necessária uma fonte para o processo")
    @URL
    private String url;

    @JsonIgnore
    @ManyToMany(mappedBy = "processos")
    private Set<Prospect> prospects = new HashSet<>();

    public Processo(Long id, Date dataDeAbertura, TipoProcesso tipo, String numeroCNJ, String descricao, String url) {
        this.id = id;
        this.dataDeAbertura = dataDeAbertura;
        this.tipo = tipo;
        this.numeroCNJ = numeroCNJ;
        this.descricao = descricao;
        this.url = url;
    }
}