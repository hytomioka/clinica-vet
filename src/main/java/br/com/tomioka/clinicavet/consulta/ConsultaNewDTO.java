package br.com.tomioka.clinicavet.consulta;

import br.com.tomioka.clinicavet.pet.Pet;
import br.com.tomioka.clinicavet.pet.PetService;
import br.com.tomioka.clinicavet.vet.Veterinario;
import br.com.tomioka.clinicavet.vet.VeterinarioRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ConsultaNewDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future(message = "Data inválida, necessário uma data futura")
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

    public ConsultaNewDTO(Consulta consulta) {
        this.horario = consulta.getHorario();
        this.petId = consulta.getPet().getId();
        this.vetEmail = consulta.getVeterinario().getEmail();
        this.descricao = consulta.getDescricao();
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
