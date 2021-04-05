package br.com.tomioka.clinicavet.repository;

import br.com.tomioka.clinicavet.modelo.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
