package br.com.tomioka.clinicavet.usuario;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Login {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converte() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
