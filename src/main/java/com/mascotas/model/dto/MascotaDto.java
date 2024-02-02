package com.mascotas.model.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
@ToString
@Builder
public class MascotaDto implements Serializable {

    private Integer id;
    private String nombre;
    private String tipo_animal;
    private String raza;
    private Date fechaNacimiento;
}
