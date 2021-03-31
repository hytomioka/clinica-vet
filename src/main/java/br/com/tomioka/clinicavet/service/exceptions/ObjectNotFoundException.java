package br.com.tomioka.clinicavet.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String mensagem) {
        super(mensagem);
    }
}
