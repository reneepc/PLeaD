package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.dtos.inspecao.NewInspecaoDTO;
import br.com.opussoftware.plead.services.InspecaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/inspecao")
public class InspecaoController {
    private final InspecaoService inspecaoService;

    public InspecaoController(InspecaoService inspecaoService) {
        this.inspecaoService = inspecaoService;
    }

    @PostMapping
    public ResponseEntity<?> recebeInspecao(@Valid @RequestBody NewInspecaoDTO newInspecaoDTO) {
        inspecaoService.save(newInspecaoDTO);
        return ResponseEntity.ok().body(newInspecaoDTO);
    }
}
