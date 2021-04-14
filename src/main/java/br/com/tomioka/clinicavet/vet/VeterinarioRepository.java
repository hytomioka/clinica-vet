package br.com.tomioka.clinicavet.vet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {
    Veterinario findByEmail(String vetEmail);
}
