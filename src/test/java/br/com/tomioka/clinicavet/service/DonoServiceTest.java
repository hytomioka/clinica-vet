package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.model.Dono;
import br.com.tomioka.clinicavet.controller.dto.DonoDTO;
import br.com.tomioka.clinicavet.model.Pet;
import br.com.tomioka.clinicavet.model.enums.TipoPet;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.configuration.exceptions.DataIntegrityException;
import br.com.tomioka.clinicavet.configuration.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DonoServiceTest {

    private DonoService service;

    @Mock
    private DonoRepository repo;

    private Optional<Dono> dono;

    @Captor
    private ArgumentCaptor<Dono> captor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service = new DonoService(repo);
        criaDonoDeTeste();
    }

    @Test
    void deveriaRetornarFalseSeDonoForInexistente() {
        when(repo.findById(dono.get().getId()))
                .thenReturn(Optional.ofNullable(null));
        assertThrows(ObjectNotFoundException.class,
                () -> service.buscaPorId(dono.get().getId()));
    }

    @Test
    void deveriaRetornarTrueSeHouverDonoExistente() {
        when(repo.findById(dono.get().getId())).thenReturn(dono);
        Dono donoMock = service.buscaPorId(dono.get().getId());
        assertTrue(donoMock != null);
    }

//    @Test
//    void deveriaSalvarUmNovoDonoNoRepositorio() {
//        when(repo.save(dono.get())).thenReturn(dono.get());
//        Dono donoMock = service.inserir(dono.get());
//        assertTrue(donoMock != null);
//    }

    @Test
    void deveriaAtualizarUmDonoSalvoNoRepositorio() {
        DonoDTO donoTesteAtualizado = criaDonoDeTesteParaAtualizarCampos();
        when(repo.findById(any())).thenReturn(dono);
        service.atualiza(dono.get().getId(), donoTesteAtualizado);
        verify(repo).save(captor.capture());
        Dono donoAtualizado = captor.getValue();

        assertEquals("Rua engenheiro Borges, 32", donoAtualizado.getEndereco());
    }

    @Test
    void deveriaRetornarExcecaoQuandoNaoForPossivelEncontrarDono() {
        DonoDTO donoTesteAtualizado = criaDonoDeTesteParaAtualizarCampos();
        when(repo.findById(any())).thenReturn(Optional.ofNullable(null));
        assertThrows(ObjectNotFoundException.class,
                () -> service.atualiza(dono.get().getId(), donoTesteAtualizado));
    }

    @Test
    void deveriaRetornarExcecaoQuandoNaoForPossivelDeletarDono() {
        when(repo.findById(any())).thenThrow(DataIntegrityViolationException.class);
        assertThrows(DataIntegrityException.class,
                () -> service.deleta(dono.get().getId()));
    }

    public void criaDonoDeTeste() {
        this.dono = Optional.of(new Dono());
        dono.get().setId(1);
        dono.get().setNome("Ana");
        dono.get().setEmail("ana@gmail.com");
        dono.get().setEndereco("Rua das pipas, 48");
        Pet pet = new Pet();
        pet.setNome("Pluto");
        pet.setTipoDoPet(TipoPet.CACHORRO);
        pet.setDono(dono.get());
        dono.get().setPets(Arrays.asList(pet));
    }

    public DonoDTO criaDonoDeTesteParaAtualizarCampos() {
        Dono donoAtualizaCampos = new Dono();
        donoAtualizaCampos.setId(1);
        donoAtualizaCampos.setNome("Ana Clara");
        donoAtualizaCampos.setEndereco("Rua engenheiro Borges, 32");
        return new DonoDTO(donoAtualizaCampos);
    }
}