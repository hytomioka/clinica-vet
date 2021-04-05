package br.com.tomioka.clinicavet.controller.exceptions;

import org.springframework.http.HttpStatus;

public class ErroPadrao {

    private Integer status;
    private String mensagem;

    public ErroPadrao(Integer status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
