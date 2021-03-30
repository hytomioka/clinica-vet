package br.com.tomioka.clinicavet.repository;

import br.com.tomioka.clinicavet.modelo.Dono;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonoRepository extends JpaRepository<Dono, Integer> {
}
