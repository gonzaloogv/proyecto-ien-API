package com.proyecto.api.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "profesor")
public class Profesores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "de_nombre_profesor")
    private String nombre;

    @Column(name = "de_apellido_profesor")
    private String apellido;

    @Column(name = "de_dni_profesor")
    private String dni;

    @Column(name = "de_email_profesor")
    private String email;

    @Column(name = "password_profesor")
    private String password;

    @Column(name = "de_carrera")
    private String carrera;
}
