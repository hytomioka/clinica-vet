package br.com.tomioka.clinicavet.controller.dto;

import br.com.tomioka.clinicavet.model.Consulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ConsultaDTO implements Serializable {

    /*
    * DTO criada para requisições PUT, onde será possível alterar somente o
    * horario e a descricao da consulta
    */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Future
    private LocalDateTime horario;

    @Length(min = 5, max = 120, message = "Deve conter entre 5 e 120 caracteres")
    private String descricao;

    public ConsultaDTO() {
    }

    public ConsultaDTO(Consulta consulta) {
        this.horario = consulta.getHorario();
        this.descricao = consulta.getDescricao();
    }

    public Consulta converte() {
        return new Consulta(horario, descricao);
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
