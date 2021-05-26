package br.com.tomioka.clinicavet.controller;

import br.com.tomioka.clinicavet.model.Dono;
import br.com.tomioka.clinicavet.controller.dto.DonoDTO;
import br.com.tomioka.clinicavet.controller.dto.DonoNewDTO;
import br.com.tomioka.clinicavet.service.DonoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/dono")
public class DonoController {

    DonoService donoService;

    public DonoController(DonoService donoService) {
        this.donoService = donoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dono> buscaPorId(@PathVariable("id") int id) {
        Dono dono = donoService.buscaPorId(id);
        return ResponseEntity.ok().body(dono);
    }

    @GetMapping
    public ResponseEntity<Page<DonoDTO>> busca(@PageableDefault Pageable paginacao,
                                               @RequestParam(required = false) String nome) {
        Page<DonoDTO> dono;
        if (nome == null) {
            dono = donoService.busca(paginacao);
        } else {
            dono = donoService.buscaPorNome(nome, paginacao);
        }
        return ResponseEntity.ok().body(dono);
    }

    @PostMapping
    public ResponseEntity<Void> insere(@Valid @RequestBody DonoNewDTO dto) {
        Dono dono = donoService.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dono.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@Valid @RequestBody DonoDTO dto, @PathVariable("id") int id) {
        Dono dono = donoService.atualiza(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable("id") int id) {
        donoService.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
