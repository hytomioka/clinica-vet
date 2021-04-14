package br.com.tomioka.clinicavet.consulta;

import br.com.tomioka.clinicavet.modelo.Pet;
import br.com.tomioka.clinicavet.service.PetService;
import br.com.tomioka.clinicavet.vet.Veterinario;
import br.com.tomioka.clinicavet.vet.VeterinarioRepository;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ConsultaNewDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime horario;
    private Integer petId;
    private String vetEmail;
    private String descricao;

    public ConsultaNewDTO() {
    }

    public ConsultaNewDTO(Consulta consulta) {
        this.horario = consulta.getHorario();
        this.petId = consulta.getPet().getId();
        this.vetEmail = consulta.getVeterinario().getEmail();
        this.descricao = consulta.getDescricao();
    }

    public Consulta converte() {
        return new Consulta(horario, descricao);
    }

    public Consulta converte(PetService petService, VeterinarioRepository vetRepository) {
        Pet pet = petService.buscaPorId(petId);
        Veterinario vet = vetRepository.findByEmail(vetEmail);
        return new Consulta(horario, pet, vet, descricao);
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getVetEmail() {
        return vetEmail;
    }

    public void setVetEmail(String vetEmail) {
        this.vetEmail = vetEmail;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
