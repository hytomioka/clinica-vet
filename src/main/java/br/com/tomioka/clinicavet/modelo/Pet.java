package br.com.tomioka.clinicavet.modelo;

import br.com.tomioka.clinicavet.modelo.enums.TipoPet;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dono_id")
    private Dono dono;

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
