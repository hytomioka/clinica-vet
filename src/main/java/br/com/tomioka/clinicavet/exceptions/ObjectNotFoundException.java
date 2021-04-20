package br.com.tomioka.clinicavet.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String mensagem) {
        super(mensagem);
    }
}
