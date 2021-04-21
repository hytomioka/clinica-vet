package br.com.tomioka.clinicavet.vet;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VeterinarioNewDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer idade;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String especialidade;

    public VeterinarioNewDTO() {
    }

    public VeterinarioNewDTO(Veterinario vet) {
        this.nome = vet.getNome();
        this.idade = vet.getIdade();
        this.email = vet.getEmail();
        this.especialidade = vet.getEspecialidade();
    }

    public Veterinario converte() {
        return new Veterinario(nome, idade, email, especialidade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
