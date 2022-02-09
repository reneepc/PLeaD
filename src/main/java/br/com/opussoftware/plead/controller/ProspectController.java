package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.services.ProspectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
