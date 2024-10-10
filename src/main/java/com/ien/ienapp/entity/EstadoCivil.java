package com.ien.ienapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "estados_civiles")
public class EstadoCivil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "de_estado_civil", nullable = false)
    private String estadoCivil;

    @Column(name = "fe_registro", nullable = false)
    private LocalDateTime feRegistro;

    @Column(name = "fe_modificacion")
    private LocalDateTime feModificacion;

    // Constructor vacío
    public EstadoCivil() {}

    public EstadoCivil(String id) {
        this.id = Integer.parseInt(id);
    }

    // Constructor que acepta un Integer
    public EstadoCivil(Integer id) {
        this.id = id;
    }
}
