package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.newProspectDTO;
import br.com.opussoftware.plead.services.ProspectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public ResponseEntity<List<Prospect>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<newProspectDTO> newProspect(@Valid @RequestBody newProspectDTO prospect) {
        return new ResponseEntity(prospect, HttpStatus.CREATED);
    }
}
