package br.com.tomioka.clinicavet.modelo;

import br.com.tomioka.clinicavet.modelo.enums.TipoPet;

public class Pet extends EntidadeBase {

    private String nome;
    private TipoPet tipo;
    private Integer idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public void setTipo(TipoPet tipo) {
        this.tipo = tipo;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
