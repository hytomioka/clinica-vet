package br.com.tomioka.clinicavet.vet;

import org.springframework.beans.factory.annotation.Autowired;
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
        Veterinario obj = service.buscaPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insere(@Valid @RequestBody Veterinario vet) {
        Veterinario obj = service.insere(vet);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(vet.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@RequestBody Veterinario vet, @PathVariable("id") int id) {
        Veterinario obj = service.atualiza(vet, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable("id") int id) {
        service.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
