package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.model.Consulta;
import br.com.tomioka.clinicavet.repository.ConsultaRepository;
import br.com.tomioka.clinicavet.controller.dto.ConsultaDTO;
import br.com.tomioka.clinicavet.controller.dto.ConsultaNewDTO;
import br.com.tomioka.clinicavet.configuration.exceptions.ObjectNotFoundException;
import br.com.tomioka.clinicavet.repository.VeterinarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConsultaService {

    private ConsultaRepository repo;

    private VeterinarioRepository veterinarioRepository;

    private PetService petService;

    public ConsultaService(ConsultaRepository repo, VeterinarioRepository vetRepository, PetService petService) {
        this.repo = repo;
        this.veterinarioRepository = vetRepository;
        this.petService = petService;
    }

    @Transactional(readOnly = true)
    public Consulta buscaPorId(int id) {
        Optional<Consulta> obj = repo.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Consulta não encontrada. Id= " + id)
        );
    }

    @Transactional
    public Consulta insere(ConsultaNewDTO dto) {
        Consulta obj = dto.converte(petService, veterinarioRepository);
        obj = repo.save(obj);
        return obj;
    }

    @Transactional
    public Consulta atualiza(int id, ConsultaDTO dto) {
        Consulta obj = dto.converte();
        Consulta objDoBanco = buscaPorId(id);
        objDoBanco.setHorario(obj.getHorario() != null ? obj.getHorario() : objDoBanco.getHorario());
        objDoBanco.setDescricao(obj.getDescricao() != null ? obj.getDescricao() : objDoBanco.getDescricao());
        obj = repo.save(objDoBanco);
        return obj;
    }

    @Transactional
    public void deleta(int id) {
        buscaPorId(id);
        repo.deleteById(id);
    }

    public Page<ConsultaDTO> busca(Pageable paginacao) {
        Page<Consulta> obj = repo.findAll(paginacao);
        return obj.map(ConsultaDTO::new);
    }
}
