package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.dto.PetNewDTO;
import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.repository.PetRepository;
import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository repo;

    private DonoRepository donoRepository;

    @Autowired
    public PetService(PetRepository petRepository, DonoRepository donoRepository) {
        this.repo = petRepository;
        this.donoRepository = donoRepository;
    }

    @Transactional(readOnly = true)
    public Pet buscaPorId(int id) {
        Optional<Pet> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id= " + id
        ));
    }

    @Transactional
    public Pet insere(PetNewDTO dto) {
        Pet obj = dto.converte(donoRepository);
        repo.save(obj);
        return obj;
    }

    @Transactional
    public Pet atualiza(PetNewDTO dto, int id) {
        Pet obj = dto.converte(donoRepository);
        Pet objDoBanco = buscaPorId(id);
        objDoBanco.setNome(obj.getNome() != null ? obj.getNome() : objDoBanco.getNome());
        objDoBanco.setIdade(obj.getIdade() != null ? obj.getIdade() : objDoBanco.getIdade());
        obj = repo.save(objDoBanco);
        return obj;
    }

    @Transactional
    public void deleta(int id) {
        /* Spring não joga exceção de DataIntegrityViolation, portanto não é necessário tratar */
        buscaPorId(id);
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Pet> buscaComFiltro(int page, int size, int tipo) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return repo.findAllByTipoDoPet(tipo, pageRequest);
    }
}
