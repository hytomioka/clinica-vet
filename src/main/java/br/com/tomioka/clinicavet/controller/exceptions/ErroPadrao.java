package br.com.tomioka.clinicavet.controller.exceptions;

import org.springframework.http.HttpStatus;

public class ErroPadrao {

    private HttpStatus status;
    private String mensagem;

    public ErroPadrao(HttpStatus status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
