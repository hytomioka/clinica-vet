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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> service.buscaPorId(2));
    }

    @Test
    void deveriaRetornarTrueSePetForExistente() {
        when(repo.findById(1))
                .thenReturn(Optional.of(pet.get()));
        Pet petMock = service.buscaPorId(1);

        assertThat(petMock).isNotNull();
        assertThat(pet.get()).isEqualTo(petMock);
        assertThat(pet.get().getNome()).isEqualTo(petMock.getNome());
        assertThat(pet.get().getDono()).isEqualTo(petMock.getDono());
    }

    @Test
    void deveriaSalvarUmPetNoRepositorio() {
        criaPetDeTesteParaRequisicaoPOSTouPUT();
        when(donoRepository.findByEmail(dono.get().getEmail()))
                .thenReturn(dono.get());
        service.insere(dto);
        verify(repo).save(captor.capture());
        Pet petSalvo = captor.getValue();

        assertThat(petSalvo).isNotNull();
        assertThat(dto.getNome()).isEqualTo(petSalvo.getNome());
        assertThat(dto.getTipoDoPet()).isEqualTo(petSalvo.getTipoDoPet().getCod());
        assertThat(dto.getDonoEmail()).isEqualTo(petSalvo.getDono().getEmail());
    }

    @Test
    void deveriaAtualizarUmPetNoRepositorio() {
        criaPetDeTesteParaRequisicaoPOSTouPUT();
        when(repo.findById(any())).thenReturn(pet);
        when(donoRepository.findByEmail(any()))
                .thenReturn(dono.get());
        service.atualiza(dto, pet.get().getId());
        verify(repo).save(captor.capture());
        Pet petAtualizado = captor.getValue();

        assertThat(petAtualizado).isNotNull();
        assertThat(dto.getNome()).isEqualTo(petAtualizado.getNome());
        assertThat(dto.getIdade()).isEqualTo(petAtualizado.getIdade());
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

    private void criaPetDeTesteParaRequisicaoPOSTouPUT() {
        this.dto = new PetNewDTO();
        dto.setNome("Mingau");
        dto.setTipoDoPet(2);
        dto.setIdade(7);
        dto.setDonoEmail(dono.get().getEmail());
    }

    private PageRequest criaPaginacao() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return pageRequest;
    }

    private List<Pet> criaListaDePets() {
        List<Pet> lista = Arrays.asList(
                new Pet("Bobby", 1),
                new Pet("Lolla", 2),
                new Pet("Groovy", 1),
                new Pet("Luz", 1),
                new Pet("Bolota", 4)
        );
        return lista;
    }
}