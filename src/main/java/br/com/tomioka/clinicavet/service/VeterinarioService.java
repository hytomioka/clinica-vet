package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.controller.dto.VeterinarioDTO;
import br.com.tomioka.clinicavet.controller.dto.VeterinarioNewDTO;
import br.com.tomioka.clinicavet.configuration.exceptions.ObjectNotFoundException;
import br.com.tomioka.clinicavet.model.Veterinario;
import br.com.tomioka.clinicavet.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VeterinarioService {

    private VeterinarioRepository repo;

    @Autowired
    public VeterinarioService(VeterinarioRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public Veterinario buscaPorId(int id) {
        Optional<Veterinario> vet = repo.findById(id);
        return vet.orElseThrow(() -> new ObjectNotFoundException(
                "Veterinario não encontrado. Id= " + id
        ));
    }

    @Transactional
    public Veterinario insere(VeterinarioNewDTO dto) {
        Veterinario vet = dto.converte();
        vet = repo.save(vet);
        return vet;
    }

    @Transactional
    public Veterinario atualiza(Veterinario vet, int id) {
        Veterinario vetDoBanco = buscaPorId(id);
        vetDoBanco.setEmail(vet.getEmail() != null ? vet.getEmail() : vetDoBanco.getEmail());
        vetDoBanco.setIdade(vet.getIdade() != null ? vet.getIdade() : vetDoBanco.getIdade());
        vetDoBanco.setEspecialidade(vet.getEspecialidade() != null
                ? vet.getEspecialidade() : vetDoBanco.getEspecialidade());
        Veterinario vetSalvo = repo.save(vetDoBanco);
        return vetSalvo;
    }

    @Transactional
    public void deleta(int id) {
        buscaPorId(id);
        repo.deleteById(id);
    }

    public Page<VeterinarioDTO> busca(Pageable paginacao) {
        Page<Veterinario> vet = repo.findAll(paginacao);
        return vet.map(VeterinarioDTO::new);
    }
}
