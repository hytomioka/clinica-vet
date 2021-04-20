package br.com.tomioka.clinicavet.modelo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Pessoa extends EntidadeBase {

    @Column(name = "nome")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @Column(name = "idade")
    @NotNull
    private Integer idade;

    @Column(name = "email")
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email
    private String email;

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
}
