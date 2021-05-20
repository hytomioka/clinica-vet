package br.com.tomioka.clinicavet.controller.dto;

import br.com.tomioka.clinicavet.model.Dono;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class DonoDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String endereco;

    public DonoDTO() {
    }

    public DonoDTO(Dono dono) {
        this.email = dono.getEmail();
        this.endereco = dono.getEndereco();
    }

    public Dono converte() {
        return new Dono(email, endereco);
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
