package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.lang.model.util.Types;
import javax.management.ConstructorParameters;
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
        MockitoAnnotations.openMocks(this);
        this.service = new DonoService(repo);
        criaDonoDeTeste();
    }

    @Test
    void deveriaRetornarFalseSeDonoForInexistente() {
        when(repo.findById(dono.get().getId()))
                .thenReturn(Optional.ofNullable(null));
//        Dono donoMock = service.buscaPorId(dono.get().getId());
        assertThrows(ObjectNotFoundException.class,
                () -> service.buscaPorId(dono.get().getId()));
    }

    @Test
    void deveriaRetornarTrueSeHouverDonoExistente() {
        when(repo.findById(dono.get().getId())).thenReturn(dono);
        Dono donoMock = service.buscaPorId(dono.get().getId());
        assertTrue(donoMock != null);
    }

    @Test
    void deveriaSalvarUmNovoDonoNoRepositorio() {
        when(repo.save(dono.get())).thenReturn(dono.get());
        Dono donoMock = service.inserir(dono.get());
        assertTrue(donoMock != null);
    }

    @Test
    void deveriaAtualizarUmDonoSalvoNoRepositorio() {
        Dono donoTesteAtualizado = criaDonoDeTesteParaAtualizarCampos();
        when(repo.findById(any())).thenReturn(dono);
        service.atualiza(dono.get().getId(), donoTesteAtualizado);
        verify(repo).save(captor.capture());
        Dono donoAtualizado = captor.getValue();

        assertEquals("Ana Clara", donoAtualizado.getNome());
        assertEquals("Rua engenheiro Borges, 32", donoAtualizado.getEndereco());
    }

    @Test
    void deveriaRetornarExcecaoQuandoNaoForPossivelEncontrarDono() {
        Dono donoTesteAtualizado = criaDonoDeTesteParaAtualizarCampos();
        when(repo.findById(any())).thenReturn(Optional.ofNullable(null));
        assertThrows(ObjectNotFoundException.class,
                () -> service.atualiza(dono.get().getId(), donoTesteAtualizado));
    }

    public void criaDonoDeTeste() {
        this.dono = Optional.of(new Dono());
        dono.get().setId(1);
        dono.get().setNome("Ana");
        dono.get().setEmail("ana@gmail.com");
        dono.get().setEndereco("Rua das pipas, 48");
    }

    public Dono criaDonoDeTesteParaAtualizarCampos() {
        Dono donoAtualizaCampos = new Dono();
        donoAtualizaCampos.setId(1);
        donoAtualizaCampos.setNome("Ana Clara");
        donoAtualizaCampos.setEndereco("Rua engenheiro Borges, 32");
        return donoAtualizaCampos;
    }
}