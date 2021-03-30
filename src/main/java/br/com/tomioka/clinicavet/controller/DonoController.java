package br.com.tomioka.clinicavet.controller;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.service.DonoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class DonoController {

    DonoService donoService;

    public DonoController(DonoService donoService) {
        this.donoService = donoService;
    }

    @GetMapping("/dono/{id}")
    public ResponseEntity<Dono> busca(@PathVariable("id") int id) {
        Optional<Dono> dono = donoService.buscaPorId(id);
        if (dono.isPresent()) {
            return ResponseEntity.ok().body(dono.get());

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
