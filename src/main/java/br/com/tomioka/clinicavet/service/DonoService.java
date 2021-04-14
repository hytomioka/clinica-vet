package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.service.exceptions.DataIntegrityException;
import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DonoService {

    DonoRepository donoRepository;

    public DonoService(DonoRepository donoRepository) {
        this.donoRepository = donoRepository;
    }

    @Transactional(readOnly = true)
    public Dono buscaPorId(int id) {
        Optional<Dono> obj = donoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Dono não encontrado. Id = " + id
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

    @Transactional
    public void deleta(int id) {
        /*
        * Necessário tratar, pois o Spring retornará uma exceção de DataIntegrityViolationException.
        * O motivo da exceção é que a chave primária desta entidade (dono.id) é uma referencia da entidade
        * Pet. Pet tem relacionamento com Dono, portanto uma chave estrangeira vinculada.
        * Nesta regra de negócio, foi determinado que não será possível DELETAR um Dono que possui Pets vinculados.
        */
        try {
            buscaPorId(id);
            donoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Dono que contêm Pets");
        }
    }
}
