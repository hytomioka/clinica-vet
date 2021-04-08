package br.com.tomioka.clinicavet.dto;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.repository.DonoRepository;

import java.io.Serializable;

/*
* Para serializar este DTO, os métodos de getter precisam estar presentes, ao contrário, o spring retornará
* uma exceção do tipo "InvalidDefinitionException"
*/

public class PetNewDTO implements Serializable {

    private String nome;
    private Integer tipoDoPet;
    private Integer idade;
    private String donoEmail;

    public PetNewDTO() {
    }

    public PetNewDTO(Pet pet) {
        this.nome = pet.getNome();
        this.tipoDoPet = pet.getTipoDoPet().getCod();
        this.idade = pet.getIdade();
        this.donoEmail = pet.getDono().getEmail();
    }

    public Pet converte(DonoRepository donoRepository) {
        Dono dono = donoRepository.findByEmail(donoEmail);
        return new Pet(nome, tipoDoPet, idade, dono);
    }

    public String getNome() {
        return nome;
    }

    public Integer getTipoDoPet() {
        return tipoDoPet;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getDonoEmail() {
        return donoEmail;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoDoPet(Integer tipoDoPet) {
        this.tipoDoPet = tipoDoPet;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setDonoEmail(String donoEmail) {
        this.donoEmail = donoEmail;
    }
}
