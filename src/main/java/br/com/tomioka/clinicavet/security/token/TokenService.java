package br.com.tomioka.clinicavet.security.token;

import br.com.tomioka.clinicavet.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${clinica.jwt.secret}")
    private String secret;

    @Value("${clinica.jwt.expiration}")
    private String expiration;


    public String geraToken(Authentication authenticate) {

        Usuario usuarioLogado = (Usuario) authenticate.getPrincipal();
        Date hoje = new Date();
        Date expiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("clinica-pet REST API")
                .setIssuedAt(hoje)
                .setExpiration(expiracao)
                .setSubject(usuarioLogado.getId().toString())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
