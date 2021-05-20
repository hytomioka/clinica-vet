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
        Optional<Veterinario> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Veterinario não encontrado. Id= " + id
        ));
    }

    @Transactional
    public Veterinario insere(VeterinarioNewDTO dto) {
        Veterinario obj = dto.converte();
        obj = repo.save(obj);
        return obj;
    }

    @Transactional
    public Veterinario atualiza(Veterinario vet, int id) {
        Veterinario objDoBanco = buscaPorId(id);
        objDoBanco.setEmail(vet.getEmail() != null ? vet.getEmail() : objDoBanco.getEmail());
        objDoBanco.setIdade(vet.getIdade() != null ? vet.getIdade() : objDoBanco.getIdade());
        objDoBanco.setEspecialidade(vet.getEspecialidade() != null
                ? vet.getEspecialidade() : objDoBanco.getEspecialidade());
        Veterinario obj = repo.save(objDoBanco);
        return obj;
    }

    @Transactional
    public void deleta(int id) {
        buscaPorId(id);
        repo.deleteById(id);
    }

    public Page<VeterinarioDTO> busca(Pageable paginacao) {
        Page<Veterinario> obj = repo.findAll(paginacao);
        return obj.map(VeterinarioDTO::new);
    }
}
