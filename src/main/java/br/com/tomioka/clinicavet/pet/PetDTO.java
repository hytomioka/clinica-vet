package br.com.tomioka.clinicavet.pet;

import br.com.tomioka.clinicavet.dono.Dono;

public class PetDTO {

    private Integer id;
    private String nome;
    private Integer tipoDoPet;
    private Integer idade;
    private Dono dono;

    public PetDTO() {
    }

    public PetDTO(Pet pet) {
        this.id = pet.getId();
        this.nome = pet.getNome();
        this.tipoDoPet = pet.getTipoDoPet().getCod();
        this.idade = pet.getIdade();
        this.dono = pet.getDono();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipoDoPet() {
        return tipoDoPet;
    }

    public void setTipoDoPet(Integer tipoDoPet) {
        this.tipoDoPet = tipoDoPet;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
