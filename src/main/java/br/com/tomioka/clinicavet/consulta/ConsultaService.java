package br.com.tomioka.clinicavet.consulta;

import br.com.tomioka.clinicavet.pet.PetService;
import br.com.tomioka.clinicavet.exceptions.ObjectNotFoundException;
import br.com.tomioka.clinicavet.vet.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConsultaService {

    private ConsultaRepository repo;

    private VeterinarioRepository veterinarioRepository;

    private PetService petService;

    @Autowired
    public ConsultaService(ConsultaRepository repo, VeterinarioRepository vetRepository, PetService petService) {
        this.repo = repo;
        this.veterinarioRepository = vetRepository;
        this.petService = petService;
    }

    @Transactional(readOnly = true)
    public Consulta buscaPorId(int id) {
        Optional<Consulta> obj = repo.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Consulta não encontrada. Id= " + id)
        );
    }

    @Transactional
    public Consulta insere(ConsultaNewDTO dto) {
        Consulta obj = dto.converte(petService, veterinarioRepository);
        obj = repo.save(obj);
        return obj;
    }

    @Transactional
    public Consulta atualiza(int id, ConsultaNewDTO dto) {
        Consulta obj = dto.converte();
        Consulta objDoBanco = buscaPorId(id);
        objDoBanco.setHorario(obj.getHorario() != null ? obj.getHorario() : objDoBanco.getHorario());
        objDoBanco.setDescricao(obj.getDescricao() != null ? obj.getDescricao() : objDoBanco.getDescricao());
        obj = repo.save(objDoBanco);
        return obj;
    }

    @Transactional
    public void deleta(int id) {
        buscaPorId(id);
        repo.deleteById(id);
    }
}
