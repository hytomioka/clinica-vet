package br.com.tomioka.clinicavet.controller;

import br.com.tomioka.clinicavet.controller.dto.ConsultaDTO;
import br.com.tomioka.clinicavet.controller.dto.ConsultaNewDTO;
import br.com.tomioka.clinicavet.model.Consulta;
import br.com.tomioka.clinicavet.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscaPorId(@PathVariable("id") int id) {
        Consulta consulta = service.buscaPorId(id);
        return ResponseEntity.ok().body(consulta);
    }

    @GetMapping
    @Cacheable(value = "listaConsultas")
    public ResponseEntity<Page<ConsultaDTO>> busca(@PageableDefault Pageable paginacao) {
        Page<ConsultaDTO> consulta = service.busca(paginacao);
        return ResponseEntity.ok().body(consulta);
    }

    @PostMapping
    @CacheEvict(value = "listaConsultas", allEntries = true)
    public ResponseEntity<ConsultaNewDTO> insere(@Valid @RequestBody ConsultaNewDTO dto) {
        Consulta consulta = service.insere(dto);
        ConsultaNewDTO consultaSalva = ConsultaNewDTO.novaConsultaFactory(consulta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(consultaSalva);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaConsultas", allEntries = true)
    public ResponseEntity<Void> atualiza(@Valid @RequestBody ConsultaDTO dto, @PathVariable("id") int id) {
        Consulta consulta = service.atualiza(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaConsultas", allEntries = true)
    public ResponseEntity<Void> deleta(@PathVariable int id) {
        service.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
