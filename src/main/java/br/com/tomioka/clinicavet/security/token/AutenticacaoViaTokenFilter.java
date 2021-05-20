package br.com.tomioka.clinicavet.security.token;

import br.com.tomioka.clinicavet.usuario.Usuario;
import br.com.tomioka.clinicavet.usuario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

//    private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/auth");

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
//        if (!this.requestMatcher.matches(httpServletRequest)) { // caso a requisição NÃO seja de autenticação
            String token = recuperaToken(httpServletRequest);
            boolean tokenEhValido = tokenService.isTokenValido(token);
            if (tokenEhValido) {
                autenticaUsuario(token);
            }
//        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // URLs que não passarão pelo filtro, more information: https://github.com/spring-projects/spring-security/issues/4368
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        List<String> urlPermitidas = Arrays.asList("/auth", "/pet");
        return urlPermitidas.contains(path);
    }

    private void autenticaUsuario(String token) {
        Integer usuarioId = tokenService.retornaUsuarioId(token);
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getPerfis());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String recuperaToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token.isEmpty() || token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.split(" ")[1];
    }
}
