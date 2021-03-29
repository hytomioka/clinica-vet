package br.com.tomioka.clinicavet.modelo;

import java.io.Serializable;

public class EntidadeBase implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
