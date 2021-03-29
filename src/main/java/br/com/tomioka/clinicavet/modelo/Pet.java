package br.com.tomioka.clinicavet.modelo;

import br.com.tomioka.clinicavet.modelo.enums.TipoPet;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pet")
public class Pet extends EntidadeBase {

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo_pet")
    private Integer tipoDoPet;

    @Column(name = "idade")
    private LocalDate dataDeNascimento;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    private Dono dono;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipo() {
        return tipoDoPet;
    }

    public void setTipo(Integer tipo) {
        this.tipoDoPet = tipo;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }
}
