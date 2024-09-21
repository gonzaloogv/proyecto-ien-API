package com.proyecto.api.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "alumno")
public class Alumnos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "de_nombre_alumno")
    private String nombre;

    @Column(name = "de_apellido_alumno")
    private String apellido;

    @Column(name = "dni_alumno")
    private String dni;

    @Column(name = "email_alumno")
    private String email;

    @Column(name = "password_alumno")
    private String password;

    @Column(name = "de_carrera")
    private String carrera;
}
