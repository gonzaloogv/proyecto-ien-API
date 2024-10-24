package com.ien.ienapp.entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nu_matricula")
    private String nuMatricula;

    @Column(name = "fe_baja")
    private LocalDateTime feBaja;

    @Column(name = "fe_ingreso")
    private LocalDateTime feIngreso;

    @Column(name = "fe_modificacion")
    private LocalDateTime feModificacion;

    @Column(name = "fe_registro")
    private LocalDateTime feRegistro;

    @OneToOne
    @JoinColumn(name = "Id", referencedColumnName = "Id") 
    private RRHH rrhh;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getFeBaja() { return feBaja; }
    public void setFeBaja(LocalDateTime feBaja) { this.feBaja = feBaja; }

    public LocalDateTime getFeIngreso() { return feIngreso; }
    public void setFeIngreso(LocalDateTime feIngreso) { this.feIngreso = feIngreso; }

    public LocalDateTime getFeModificacion() { return feModificacion; }
    public void setFeModificacion(LocalDateTime feModificacion) { this.feModificacion = feModificacion; }

    public LocalDateTime getFeRegistro() { return feRegistro; }
    public void setFeRegistro(LocalDateTime feRegistro) { this.feRegistro = feRegistro; }

    public String getNuMatricula() { return nuMatricula; }
    public void setNuMatricula(String nuMatricula) { this.nuMatricula = nuMatricula; }

    public RRHH getRrhh() { return rrhh; }
    public void setRrhh(RRHH rrhh) { this.rrhh = rrhh; }
}