package br.com.tomioka.clinicavet.service.exceptions;

public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String msg) {
        super(msg);
    }
}
