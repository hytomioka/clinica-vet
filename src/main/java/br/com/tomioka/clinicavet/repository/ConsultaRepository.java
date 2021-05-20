package br.com.tomioka.clinicavet.repository;

import br.com.tomioka.clinicavet.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
}
