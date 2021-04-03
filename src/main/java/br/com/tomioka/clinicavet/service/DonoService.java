package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DonoService {

    DonoRepository donoRepository;

    public DonoService(DonoRepository donoRepository) {
        this.donoRepository = donoRepository;
    }

    public Dono buscaPorId(int id) {
        Optional<Dono> obj = donoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id = " + id
        ));
    }

    @Transactional
    public Dono inserir(Dono dono) {
        Dono obj = donoRepository.save(dono);
        return obj;
    }

    @Transactional
    public Dono atualiza(int id, Dono dono) {
        Dono obj = buscaPorId(id);
        obj.setNome(dono.getNome());
        obj.setEndereco(dono.getEndereco());
        obj = donoRepository.save(obj);
        return obj;
    }


    public void deleta(int id) {
        buscaPorId(id);
        donoRepository.deleteById(id);
    }
}
