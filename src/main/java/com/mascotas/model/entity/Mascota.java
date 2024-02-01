package com.mascotas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "mascotas")
public class Mascota implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipoAnimal")
    private String tipo_animal;
    @Column(name = "raza")
    private String raza;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
}
