package br.com.tomioka.clinicavet.configuration.exceptions;

public class ErroCampo  {

    private String field;
    private String mensagem;

    public ErroCampo(String field, String mensagem) {
        this.field = field;
        this.mensagem = mensagem;
    }

    public String getField() {
        return field;
    }

    public String getMensagem() {
        return mensagem;
    }
}
