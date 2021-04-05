package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.repository.PetRepository;
import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private PetRepository repo;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.repo = petRepository;
    }

    public Pet buscaPorId(int id) {
        Optional<Pet> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id= " + id
        ));
    }
}
