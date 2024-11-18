package com.ien.ienapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asistencias")
public class Asistencias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_comisiones_detalles", nullable = false)
    private ComisionDetalle comisionDetalle;

    @Column(name = "fe_asistencia", nullable = false)
    private Date feAsistencia;

    @Column(name = "nu_asistencia", nullable = false)
    private Double nuAsistencia;

    public Asistencias() {}
}
