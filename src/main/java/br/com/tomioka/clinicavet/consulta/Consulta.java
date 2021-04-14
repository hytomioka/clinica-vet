package br.com.tomioka.clinicavet.consulta;

import br.com.tomioka.clinicavet.modelo.EntidadeBase;
import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.vet.Veterinario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
public class Consulta extends EntidadeBase {

    @Column(name = "data_horario")
    private LocalDateTime horario;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Veterinario veterinario;

    @Column(name = "descricao")
    private String descricao;

    public Consulta() {
    }

    public Consulta(LocalDateTime horario, Pet pet, Veterinario vet, String descricao) {
        super();
        this.horario = horario;
        this.pet = pet;
        this.veterinario = vet;
        this.descricao = descricao;
    }

    public Consulta(LocalDateTime horario, String descricao) {
        super();
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}
