package br.com.tomioka.clinicavet.controller.dto;

import br.com.tomioka.clinicavet.model.Consulta;
import br.com.tomioka.clinicavet.model.Pet;
import br.com.tomioka.clinicavet.service.PetService;
import br.com.tomioka.clinicavet.model.Veterinario;
import br.com.tomioka.clinicavet.repository.VeterinarioRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ConsultaNewDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Future(message = "Data inválida, necessário uma data futura")
    @NotNull(message = "Preenchimento obrigatório")
    private LocalDateTime horario;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer petId;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "E-mail inválido")
    private String vetEmail;

    @NotNull(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "Deve conter entre 5 e 120 caracteres")
    private String descricao;

    public ConsultaNewDTO() {
    }

    private ConsultaNewDTO(Consulta consulta) {
        this.horario = consulta.getHorario();
        this.petId = consulta.getPet().getId();
        this.vetEmail = consulta.getVeterinario().getEmail();
        this.descricao = consulta.getDescricao();
    }

    public static ConsultaNewDTO novaConsultaFactory(Consulta consulta) {
        return new ConsultaNewDTO(consulta);
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
