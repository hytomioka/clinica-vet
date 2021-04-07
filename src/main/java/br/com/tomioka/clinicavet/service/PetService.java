package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.dto.PetNewDTO;
import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.repository.PetRepository;
import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository repo;

    private DonoRepository donoRepository;

    @Autowired
    public PetService(PetRepository petRepository, DonoRepository donoRepository) {
        this.repo = petRepository;
        this.donoRepository = donoRepository;
    }

    public Pet buscaPorId(int id) {
        Optional<Pet> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id= " + id
        ));
    }

    @Transactional
    public Pet insere(PetNewDTO dto) {
        Pet obj = dto.converte(donoRepository);
        repo.save(obj);
        return obj;
    }

    @Transactional
    public Pet atualiza(PetNewDTO dto, int id) {
        Pet obj = dto.converte(donoRepository);
        Pet objDoBanco = buscaPorId(id);
        objDoBanco.setNome(obj.getNome() != null ? obj.getNome() : objDoBanco.getNome());
        objDoBanco.setIdade(obj.getIdade() != null ? obj.getIdade() : objDoBanco.getIdade());
        obj = repo.save(objDoBanco);
        return obj;
    }
}
