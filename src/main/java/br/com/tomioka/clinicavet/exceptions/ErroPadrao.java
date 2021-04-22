package br.com.tomioka.clinicavet.exceptions;

import org.springframework.http.HttpStatus;

public class ErroPadrao {

    private Integer status;
    private String mensagem;

    public ErroPadrao(Integer status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public ErroPadrao(String mensagem) {
    }

    public Integer getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

}
