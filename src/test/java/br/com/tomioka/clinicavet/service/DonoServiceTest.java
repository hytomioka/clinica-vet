package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.repository.DonoRepository;
import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class DonoServiceTest {

    private DonoService service;

    @Mock
    private DonoRepository repo;

    private Optional<Dono> dono;

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

    public void criaDonoDeTeste() {
        this.dono = Optional.of(new Dono());
        dono.get().setId(1);
        dono.get().setNome("Ana");
        dono.get().setEmail("ana@gmail.com");
    }
}