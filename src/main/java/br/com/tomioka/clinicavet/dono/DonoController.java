package br.com.tomioka.clinicavet.dono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/dono")
public class DonoController {

    DonoService donoService;

    // Se houver um único construtor, é possível omitir @Autowired
    public DonoController(DonoService donoService) {
        this.donoService = donoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dono> busca(@PathVariable("id") int id) {
        Dono dono = donoService.buscaPorId(id);
        return ResponseEntity.ok().body(dono);
    }

    @PostMapping
    public ResponseEntity<Void> insere(@Valid @RequestBody DonoNewDTO dto) {
        Dono obj = donoService.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@Valid @RequestBody DonoDTO dto, @PathVariable("id") int id) {
        Dono obj = donoService.atualiza(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable("id") int id) {
        donoService.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
