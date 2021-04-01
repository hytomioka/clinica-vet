package br.com.tomioka.clinicavet.controller;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.service.DonoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/dono")
public class DonoController {

    DonoService donoService;

    // Se houver um único construtor, é possível omitir @Autowired
    public DonoController(DonoService donoService) {
        this.donoService = donoService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Dono> busca(@PathVariable("id") int id) {
        Dono dono = donoService.buscaPorId(id);
        return ResponseEntity.ok().body(dono);
    }

    @PostMapping
    public ResponseEntity<Void> insere(@RequestBody Dono dono) {
        Dono obj = donoService.inserir(dono);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
