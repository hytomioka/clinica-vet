package br.com.tomioka.clinicavet.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscaPorId(@PathVariable("id") int id) {
        Consulta obj = service.buscaPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insere(@Valid @RequestBody ConsultaNewDTO dto) {
        Consulta obj = service.insere(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@Valid @RequestBody ConsultaDTO dto, @PathVariable("id") int id) {
        Consulta obj = service.atualiza(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable int id) {
        service.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
