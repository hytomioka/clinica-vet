package br.com.tomioka.clinicavet.repository;

import br.com.tomioka.clinicavet.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {
    Veterinario findByEmail(String vetEmail);
}
