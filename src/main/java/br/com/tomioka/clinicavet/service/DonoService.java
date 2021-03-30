package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonoService {

    DonoRepository donoRepository;

    public DonoService(DonoRepository donoRepository) {
        this.donoRepository = donoRepository;
    }

    public Optional<Dono> buscaPorId(int id) {
        Optional<Dono> dono = donoRepository.findById(id);
        return dono;
    }
}
