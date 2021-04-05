package br.com.tomioka.clinicavet.controller;

import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
