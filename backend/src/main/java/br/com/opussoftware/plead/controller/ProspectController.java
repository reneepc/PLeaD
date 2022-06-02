package br.com.opussoftware.plead.controller;

import br.com.opussoftware.plead.domain.Prospect;
import br.com.opussoftware.plead.dtos.NewProspectDTO;
import br.com.opussoftware.plead.services.ProspectPFService;
import br.com.opussoftware.plead.services.ProspectPJService;
import br.com.opussoftware.plead.services.ProspectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import java.util.List;

@RestController
@RequestMapping("/prospects")
@Api(tags = "Criação e Busca de Prospects")
public class ProspectController {
    private final ProspectService prospectService;
    private final ProspectPFService prospectPFService;
    private final ProspectPJService prospectPJService;

    public ProspectController(ProspectService service, ProspectPFService prospectPFService,
                              ProspectPJService prospectPJService) {
        this.prospectService = service;
        this.prospectPFService = prospectPFService;
        this.prospectPJService = prospectPJService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca prospect pelo id")
    public ResponseEntity<Prospect> findById(@PathVariable Long id) {
        Prospect prospect = prospectService.findById(id);
        return ResponseEntity.ok().body(prospect);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deleta um prospect por seu id")
    public ResponseEntity<Long> deleteProspectById(@PathVariable Long id) {
        prospectService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "cpf", params = "cpf")
    @ApiOperation(value = "Busca prospect do tipo Pessoa Física com o devido CPF")
    public ResponseEntity<Prospect> findByCpf(@RequestParam String cpf) {
        Prospect prospect = prospectPFService.findByCpf(cpf);
        return ResponseEntity.ok().body(prospect);
    }

    @GetMapping(value = "cnpj", params = "cnpj")
    @ApiOperation(value = "Busca prospect do tipo Pessoa Jurídica com o devido CNPJ")
    public ResponseEntity<Prospect> findByCnpj(@RequestParam String cnpj) {
        Prospect prospect = prospectPJService.findByCnpj(cnpj);
        return ResponseEntity.ok().body(prospect);
    }

    @GetMapping(value = "/nome", params = "nome")
    @ApiOperation(value = "Busca paginação de prospects do tipo Pessoa Física, tal que o seu nome contenha a string" +
            " nome")
    public ResponseEntity<Page<Prospect>> findProspectPFsByNome(@RequestParam String nome,
                                           @PageableDefault(sort = "id",
                                                   direction = Sort.Direction.ASC,
                                                   page = 0,
                                                   size = 12)
                                           Pageable page) {
        Page<Prospect> prospects = prospectPFService.findAllByNome(nome, page);
        return ResponseEntity.ok().body(prospects);
    }

    @GetMapping(value = "/razao", params = "razao")
    @ApiOperation(value = "Busca paginação de prospects do tipo Pessoa Jurídica, tal que a sua razão social " +
            "contenha a string razao")
    public ResponseEntity<Page<Prospect>> findProspectPJsByRazaoSocial(@RequestParam(name = "razao") String razaoSocial,
                                                     @PageableDefault(sort = "id",
                                                             direction = Sort.Direction.ASC,
                                                             page = 0,
                                                             size = 12)
                                                             Pageable page) {
        Page<Prospect> prospects = prospectPJService.findAllByRazaoSocial(razaoSocial, page);
        return ResponseEntity.ok().body(prospects);
    }


    @GetMapping
    @ApiOperation(value = "Busca paginação com todos os prospects")
    public ResponseEntity<Page<Prospect>> findAll(
            @PageableDefault(sort = "id",
                            direction = Sort.Direction.ASC,
                            page = 0,
                            size = 12)
                Pageable page) {

        return ResponseEntity.ok().body(prospectService.findAll(page));
    }

    @GetMapping(params = "min")
    @ApiOperation(value = "Busca prospects com renda acima de um certo valor")
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
    @ApiOperation(value = "Cria prospect",
    notes = "Cria prospect para ser enviado para inspeção")
    public ResponseEntity<?> newProspect(@Valid @RequestBody NewProspectDTO newProspectDTO) {
        Prospect prospect = prospectService.save(newProspectDTO);
        var location = URI.create("/prospects/" + prospect.getId());
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/batch")
    @ApiOperation(value = "Cria prospects em batch",
    notes = "Realiza a criação de prospects baseado na lista enviada")
    public ResponseEntity<List<Prospect>> newProspectBatch(@Valid @RequestBody List<NewProspectDTO> newProspectDTOList) {
        List<Prospect> prospects = prospectService.saveAll(newProspectDTOList);
        return ResponseEntity.ok().body(prospects);
    }
}
