package br.com.tomioka.clinicavet.controller;

import br.com.tomioka.clinicavet.model.Veterinario;
import br.com.tomioka.clinicavet.controller.dto.VeterinarioDTO;
import br.com.tomioka.clinicavet.controller.dto.VeterinarioNewDTO;
import br.com.tomioka.clinicavet.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/vet")
public class VeterinarioController {

    @Autowired
    private VeterinarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> buscaPorId(@PathVariable("id") int id) {
        Veterinario veterinario = service.buscaPorId(id);
        return ResponseEntity.ok().body(veterinario);
    }

    @GetMapping
    public ResponseEntity<Page<VeterinarioDTO>> busca(@PageableDefault Pageable paginacao) {
        Page<VeterinarioDTO> dto = service.busca(paginacao);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insere(@Valid @RequestBody VeterinarioNewDTO dto) {
        Veterinario veterinario = service.insere(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(veterinario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@Valid @RequestBody Veterinario vet, @PathVariable("id") int id) {
        Veterinario veterinario = service.atualiza(vet, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable("id") int id) {
        service.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
