package br.com.tomioka.clinicavet.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "dono")
public class Dono extends Pessoa {

    @Column(name = "endereco")
    private String Endereco;

    @OneToMany(mappedBy = "dono")
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
