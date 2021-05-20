package br.com.tomioka.clinicavet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "dono")
public class Dono extends Pessoa {

    @Column(name = "endereco")
    private String endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "dono")
    private List<Pet> pets;

    public Dono() {
    }

    public Dono(String email, String endereco) {
        super(email);
        this.endereco = endereco;
    }

    public Dono(String nome, Integer idade, String email, String endereco) {
        super(nome, idade, email);
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
