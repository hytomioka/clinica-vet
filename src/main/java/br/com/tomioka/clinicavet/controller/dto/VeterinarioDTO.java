package br.com.tomioka.clinicavet.controller.dto;

import br.com.tomioka.clinicavet.model.Veterinario;

public class VeterinarioDTO {

    private Integer id;
    private String nome;
    private String email;
    private String especialidade;

    public VeterinarioDTO() {
    }

    public VeterinarioDTO(Veterinario vet) {
        this.id = vet.getId();
        this.nome = vet.getNome();
        this.email = vet.getEmail();
        this.especialidade = vet.getEspecialidade();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
