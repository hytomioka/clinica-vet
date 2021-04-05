package br.com.tomioka.clinicavet.dto;

import br.com.tomioka.clinicavet.modelo.Dono;
import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.repository.DonoRepository;

public class PetNewDTO {

    private String nome;
    private Integer tipoDoPet;
    private Integer idade;
    private String donoEmail;

    public Pet converte(DonoRepository donoRepository) {
        Dono dono = donoRepository.findByEmail(donoEmail);
        return new Pet(nome, tipoDoPet, idade, dono);
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
