package com.ien.ienapp.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "categorias_rrhh")
public class CategoriaRrhh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "de_categoria_rrhh", nullable = false)
    private String categoriaRrhh;

    @Column(name = "fe_registro", nullable = false)
    private LocalDateTime feRegistro;

    @Column(name = "fe_modificacion")
    private LocalDateTime feModificacion;

    public CategoriaRrhh() {}

    public CategoriaRrhh(String id) {
        this.id = Integer.parseInt(id);
    }

    // Constructor que acepta un Integer
    public CategoriaRrhh(Integer id) {
        this.id = id;
    }
}
