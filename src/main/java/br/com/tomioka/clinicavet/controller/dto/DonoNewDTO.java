package br.com.tomioka.clinicavet.controller.dto;

import br.com.tomioka.clinicavet.model.Dono;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DonoNewDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer idade;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String endereco;

    public DonoNewDTO() {
    }

    public DonoNewDTO(Dono dono) {
        this.nome = dono.getNome();
        this.idade = dono.getIdade();
        this.email = dono.getEmail();
        this.endereco = dono.getEndereco();
    }

    public Dono converte() {
        return new Dono(nome, idade, email, endereco);
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
