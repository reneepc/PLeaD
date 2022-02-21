package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.repositories.ProspectPJRepository;
import br.com.opussoftware.plead.services.ProspectPFService;
import br.com.opussoftware.plead.services.ProspectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/prospects")
public class ProspectController {
    private final ProspectService prospectService;
    private final ProspectPFService prospectPFService;
    private final ProspectPJRepository prospectPJService;

    public ProspectController(ProspectService service, ProspectPFService prospectPFService, ProspectPJRepository prospectPJService) {
        this.prospectService = service;
        this.prospectPFService = prospectPFService;
        this.prospectPJService = prospectPJService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prospect> findById(@PathVariable Long id) {
        Prospect prospect = prospectService.findById(id);
        return ResponseEntity.ok().body(prospect);
    }

    @GetMapping(value = "/{nome}", params = "nome")
    public ResponseEntity<Page<Prospect>> findProspectPFsByNome(@PathVariable String nome,
                                               @PageableDefault(sort = "id",
                                                       direction = Sort.Direction.ASC,
                                                       page = 0,
                                                       size = 12)
                                               Pageable page) {
        Page<Prospect> prospects = prospectPFService.findAllByNome(nome, page);
        return ResponseEntity.ok().body(prospects);
    }

    @GetMapping(value = "/{razao}", params = "razao")
    public ResponseEntity<Page<Prospect>> findProspectPJsByRazaoSocial(@PathVariable(name = "razao") String razaoSocial,
                                                     @PageableDefault(sort = "id",
                                                             direction = Sort.Direction.ASC,
                                                             page = 0,
                                                             size = 12)
                                                             Pageable page) {
        Page<Prospect> prospects = prospectPJService.findAllByRazaoSocial(razaoSocial, page);
        return ResponseEntity.ok().body(prospects);
    }


    @GetMapping
    public ResponseEntity<Page<Prospect>> findAll(
            @PageableDefault(sort = "id",
                            direction = Sort.Direction.ASC,
                            page = 0,
                            size = 12)
                Pageable page) {

        return ResponseEntity.ok().body(prospectService.findAll(page));
    }

    @GetMapping(params = "min")
    public ResponseEntity<Page<Prospect>> findAllByRendaAnualMinima(
            @RequestParam(name = "min", defaultValue = "0") BigDecimal rendaAnualMinima,
            @PageableDefault(sort = "id",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 12)
            Pageable page) {
        return ResponseEntity.ok().body(prospectService.findAllByRendaMinima(rendaAnualMinima, page));
    }

    @PostMapping
    public ResponseEntity<?> newProspect(@Valid @RequestBody NewProspectDTO newProspectDTO) {
        Prospect prospect = prospectService.save(newProspectDTO);
        var location = URI.create("/prospects/" + prospect.getId());
        return ResponseEntity.created(location).build();
    }
}
