package br.com.tomioka.clinicavet.security;

import br.com.tomioka.clinicavet.usuario.Usuario;
import br.com.tomioka.clinicavet.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AutenticacaoServiceTest {

    private AutenticacaoService authService;
    private Usuario usuario;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
        this.authService = new AutenticacaoService(usuarioRepository);
        criaUsuarioDeTeste();
    }

    @Test
    void deveriaBuscarUsuarioNoBancoDeDados() {
        String email = "usuario@email.com";
        when(usuarioRepository.findByLogin(email))
                .thenReturn(Optional.of(this.usuario));
        Usuario usuarioMock = (Usuario) authService.loadUserByUsername(email);

        assertThat(usuarioMock).isNotNull();
    }

    @Test
    void deveriaRetornarExceptionParaUsuarioNaoEncontrado() {
        when(usuarioRepository.findByLogin("any"))
                .thenReturn(null);

        assertThatExceptionOfType(UsernameNotFoundException.class).isThrownBy(
                () -> authService.loadUserByUsername(any())
        );
    }
    
    private void criaUsuarioDeTeste() {
        this.usuario = new Usuario("usuario@email.com", "123");
    }
}