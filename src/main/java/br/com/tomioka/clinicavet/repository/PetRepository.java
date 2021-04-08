package br.com.tomioka.clinicavet.repository;

import br.com.tomioka.clinicavet.modelo.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    Page<Pet> findAllByTipoDoPet(int tipo, Pageable pageRequest);
}
