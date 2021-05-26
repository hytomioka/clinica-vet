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
        Optional<Consulta> consulta = repo.findById(id);
        return consulta.orElseThrow(
                () -> new ObjectNotFoundException("Consulta não encontrada. Id= " + id)
        );
    }

    @Transactional
    public Consulta insere(ConsultaNewDTO dto) {
        Consulta consulta = dto.converte(petService, veterinarioRepository);
        consulta = repo.save(consulta);
        return consulta;
    }

    @Transactional
    public Consulta atualiza(int id, ConsultaDTO dto) {
        Consulta consulta = dto.converte();
        Consulta consultaDoBanco = buscaPorId(id);
        consultaDoBanco.setHorario(consulta.getHorario() != null ? consulta.getHorario() : consultaDoBanco.getHorario());
        consultaDoBanco.setDescricao(consulta.getDescricao() != null ? consulta.getDescricao() : consultaDoBanco.getDescricao());
        consulta = repo.save(consultaDoBanco);
        return consulta;
    }

    @Transactional
    public void deleta(int id) {
        buscaPorId(id);
        repo.deleteById(id);
    }

    public Page<ConsultaDTO> busca(Pageable paginacao) {
        Page<Consulta> consulta = repo.findAll(paginacao);
        return consulta.map(ConsultaDTO::new);
    }
}
