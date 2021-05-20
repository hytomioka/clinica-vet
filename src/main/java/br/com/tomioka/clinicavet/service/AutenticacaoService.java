package br.com.tomioka.clinicavet.service;

import br.com.tomioka.clinicavet.model.usuario.Usuario;
import br.com.tomioka.clinicavet.repository.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("prod")
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository repo;

    public AutenticacaoService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repo.findByLogin(email);
        return usuario.orElseThrow(
                () -> new UsernameNotFoundException("Dados Inválidos")
        );
    }
}
