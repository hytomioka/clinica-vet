package br.com.tomioka.clinicavet.pet;

import br.com.tomioka.clinicavet.dono.Dono;
import br.com.tomioka.clinicavet.modelo.EntidadeBase;
import br.com.tomioka.clinicavet.modelo.enums.TipoPet;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet extends EntidadeBase {

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo_pet")
    private Integer tipoDoPet;

    @Column(name = "idade")
    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    private Dono dono;

    public Pet() {
        super();
    }

    public Pet(String nome, Integer tipoDoPet) {
        this.nome = nome;
        this.tipoDoPet = tipoDoPet;
    }

    public Pet(String nome, Integer tipoDoPet, Integer idade, Dono dono) {
        super();
        this.nome = nome;
        this.tipoDoPet = tipoDoPet;
        this.idade = idade;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public TipoPet getTipoDoPet() {
        return TipoPet.toEnum(tipoDoPet);
    }

    public void setTipoDoPet(TipoPet tipoDoPet) {
        this.tipoDoPet = tipoDoPet.getCod();
    }
}
