package br.com.tomioka.clinicavet.dono;

import br.com.tomioka.clinicavet.dono.Dono;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonoRepository extends JpaRepository<Dono, Integer> {
    Dono findByEmail(String donoEmail);

    Page<Dono> findByNomeIgnoreCase(String nome, Pageable paginacao);
}
