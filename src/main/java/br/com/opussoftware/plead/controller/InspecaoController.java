package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.dtos.inspecao.NewInspecaoDTO;
import br.com.opussoftware.plead.services.InspecaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/inspecao")
@Api(tags = {"Inspeção de Prospects"})
public class InspecaoController {
    private final InspecaoService inspecaoService;

    public InspecaoController(InspecaoService inspecaoService) {
        this.inspecaoService = inspecaoService;
    }

    @PostMapping
    @ApiOperation(value = "Recebe processos e midias negativas associadas a um prospect",
            notes = "Recebe um id de prospect e duas listas, uma com processos e outra mídia negativas," +
                    "as quais serão associadas a este prospect")
    public ResponseEntity<?> recebeInspecao(@Valid @RequestBody NewInspecaoDTO newInspecaoDTO) {
        inspecaoService.save(newInspecaoDTO);
        return ResponseEntity.ok().body(newInspecaoDTO);
    }
}
