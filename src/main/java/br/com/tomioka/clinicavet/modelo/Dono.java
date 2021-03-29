package br.com.tomioka.clinicavet.modelo;

import java.util.List;

public class Dono extends Pessoa {

    private String Endereco;
    private List<Pet> pets;

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
