package br.com.tomioka.clinicavet.dono;

import br.com.tomioka.clinicavet.modelo.Pessoa;
import br.com.tomioka.clinicavet.pet.Pet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "dono")
public class Dono extends Pessoa {

    @Column(name = "endereco")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String Endereco;

    @JsonIgnore
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
