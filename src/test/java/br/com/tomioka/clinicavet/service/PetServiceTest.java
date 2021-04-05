package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.dto.PetNewDTO;
import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.modelo.enums.TipoPet;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.repository.PetRepository;
import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.lang.model.util.Types;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PetServiceTest {

    private PetService service;

    @Mock
    private PetRepository repo;

    @Mock
    private DonoRepository donoRepository;

    @Captor
    private ArgumentCaptor<Pet> captor;

    private Optional<Dono> dono;
    private Optional<Pet> pet;
    private PetNewDTO dto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.service = new PetService(repo, donoRepository);
        criaDonoEPetDeTeste();
    }

    @Test
    void deveriaRetornarExcecaoSePetForInexistente() {
        when(repo.findById(2))
                .thenReturn(Optional.ofNullable(null));
        assertThrows(ObjectNotFoundException.class,
                () -> service.buscaPorId(2));
    }

    @Test
    void deveriaRetornarTrueSePetForExistente() {
        when(repo.findById(1))
                .thenReturn(Optional.of(pet.get()));
        Pet petMock = service.buscaPorId(1);

        assertTrue(pet.get() == petMock);
        assertEquals("Thor", petMock.getNome());
        assertEquals(pet.get().getDono(), petMock.getDono());
    }

    @Test
    void deveriaSalvarUmPetNoRepositorio() {
        criaPetDeTesteParaRequisicaoPOST();
        when(donoRepository.findByEmail(dono.get().getEmail()))
                .thenReturn(dono.get());
        service.insere(dto);
        verify(repo).save(captor.capture());
        Pet petCaptor = captor.getValue();

        assertEquals("Mingau", petCaptor.getNome());
        assertEquals("Gato", petCaptor.getTipoDoPet().getDescricao());
        assertEquals(dono.get(), petCaptor.getDono());
    }

    private void criaDonoEPetDeTeste() {
        this.dono = Optional.of(new Dono());
        dono.get().setId(1);
        dono.get().setNome("Mônica");
        dono.get().setIdade(23);
        dono.get().setEmail("monica@email.com");
        dono.get().setEndereco("Rua da Árvore Verde, 2");
        this.pet = Optional.of(new Pet());
        pet.get().setNome("Thor");
        pet.get().setTipoDoPet(TipoPet.CACHORRO);
        pet.get().setId(1);
        pet.get().setDono(dono.get());
        dono.get().setPets(Arrays.asList(pet.get()));
    }

    private void criaPetDeTesteParaRequisicaoPOST() {
        this.dto = new PetNewDTO();
        dto.setNome("Mingau");
        dto.setTipoDoPet(2);
        dto.setIdade(7);
        dto.setDonoEmail(dono.get().getEmail());
    }
}