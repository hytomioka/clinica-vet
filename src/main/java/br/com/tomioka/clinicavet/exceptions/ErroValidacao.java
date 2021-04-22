package br.com.tomioka.clinicavet.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao {

    private List<ErroCampo> erros = new ArrayList<>();

    public ErroValidacao(Integer status, String mensagem) {
        super(status, mensagem);
    }

    public List<ErroCampo> getErros() {
        return erros;
    }

    public void addErros(ErroCampo erro) {
        erros.add(erro);
    }
}
