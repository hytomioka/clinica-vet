package br.com.tomioka.clinicavet.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> busca(@PathVariable int id) {
        Pet obj = petService.buscaPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<PetNewDTO>> buscaTodos(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "tipo", defaultValue = "") int tipo
    ) {
        Page<Pet> obj = petService.buscaComFiltro(page, size, tipo);
        Page<PetNewDTO> objDto = obj.map(e -> new PetNewDTO(e));
        return ResponseEntity.ok().body(objDto);
    }

    @PostMapping
    public ResponseEntity<Void> insere(@Valid @RequestBody PetNewDTO dto) {
        Pet obj = petService.insere(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // não utilizar !!
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@Valid @RequestBody PetNewDTO dto, @PathVariable("id") int id) {
        Pet obj = petService.atualiza(dto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable("id") int id) {
        petService.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
