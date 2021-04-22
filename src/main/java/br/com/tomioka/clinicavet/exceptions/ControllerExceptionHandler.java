package br.com.tomioka.clinicavet.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErroPadrao> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DataIntegrityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroPadrao> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroPadrao> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ErroValidacao erro = new ErroValidacao(HttpStatus.BAD_REQUEST.value(), "Erro de validação");
        for (FieldError err : e.getBindingResult().getFieldErrors()) {
            erro.getErros().add(new ErroCampo(err.getField(), err.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
