package br.com.tomioka.clinicavet.controller;

import br.com.tomioka.clinicavet.configuration.security.token.TokenDto;
import br.com.tomioka.clinicavet.configuration.security.token.TokenService;
import br.com.tomioka.clinicavet.model.usuario.Login;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile("prod")
public class AutenticacaoController {

    private AuthenticationManager authManager;
    private TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid Login dto) {
        UsernamePasswordAuthenticationToken dadosLogin = dto.converte();
        try {
            Authentication authenticate = authManager.authenticate(dadosLogin);
            String token = tokenService.geraToken(authenticate);
            return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
