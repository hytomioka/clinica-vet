package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.controller.dto.DonoDTO;
import br.com.tomioka.clinicavet.controller.dto.DonoNewDTO;
import br.com.tomioka.clinicavet.model.Dono;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.configuration.exceptions.DataIntegrityException;
import br.com.tomioka.clinicavet.configuration.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Dono inserir(DonoNewDTO dto) {
        Dono obj = dto.converte();
        obj = donoRepository.save(obj);
        return obj;
    }

    @Transactional
    public Dono atualiza(int id, DonoDTO dto) {
        Dono obj = dto.converte();
        Dono objDoBanco = buscaPorId(id);
        objDoBanco.setEmail(obj.getEmail() != null ? obj.getEmail() : objDoBanco.getEmail());
        objDoBanco.setEndereco(obj.getEndereco() != null ? obj.getEndereco() : objDoBanco.getEndereco());
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

    @Transactional(readOnly = true)
    public Page<DonoDTO> busca(Pageable paginacao) {
        Page<Dono> obj = donoRepository.findAll(paginacao);
        return obj.map(DonoDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<DonoDTO> buscaPorNome(String nome, Pageable paginacao) {
        Page<Dono> obj = donoRepository.findByNomeIgnoreCase(nome, paginacao);
        return obj.map(DonoDTO::new);
    }
}
