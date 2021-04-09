package br.com.tomioka.clinicavet.vet;

import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VeterinarioService {

    VeterinarioRepository repo;

    @Autowired
    public VeterinarioService(VeterinarioRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public Veterinario buscaPorId(int id) {
        Optional<Veterinario> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id= " + id
        ));
    }

    @Transactional
    public Veterinario insere(Veterinario vet) {
        Veterinario obj = repo.save(vet);
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
}
