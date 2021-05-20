package br.com.tomioka.clinicavet.controller;

import br.com.tomioka.clinicavet.model.Pet;
import br.com.tomioka.clinicavet.controller.dto.PetDTO;
import br.com.tomioka.clinicavet.controller.dto.PetNewDTO;
import br.com.tomioka.clinicavet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<PetDTO>> buscaTodos(Pageable paginacao) {
        Page<PetDTO> dto = petService.busca(paginacao);
        return ResponseEntity.ok().body(dto);
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
