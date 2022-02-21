package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.services.ProspectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/prospects")
public class ProspectController {
    private final ProspectService service;

    public ProspectController(ProspectService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prospect> findById(@PathVariable Long id) {
        Prospect prospect = service.findById(id);
        return ResponseEntity.ok().body(prospect);
    }

    @GetMapping
    public ResponseEntity<Page<Prospect>> findAll(
            @PageableDefault(sort = "id",
                            direction = Sort.Direction.ASC,
                            page = 0,
                            size = 10)
                Pageable page) {

        return ResponseEntity.ok().body(service.findAll(page));
    }

    @PostMapping
    public ResponseEntity<?> newProspect(@Valid @RequestBody NewProspectDTO newProspectDTO) {
        Prospect prospect = service.save(newProspectDTO);
        var location = URI.create("/prospects/" + prospect.getId());
        return ResponseEntity.created(location).build();
    }
}
