package br.com.tomioka.clinicavet.controller.exceptions;

import br.com.tomioka.clinicavet.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroPadrao> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
