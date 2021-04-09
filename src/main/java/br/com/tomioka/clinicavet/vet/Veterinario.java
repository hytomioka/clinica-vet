package br.com.tomioka.clinicavet.vet;

import br.com.tomioka.clinicavet.modelo.Pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "veterinario")
public class Veterinario extends Pessoa {

    @Column(name = "especialidade")
    private String especialidade;

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}