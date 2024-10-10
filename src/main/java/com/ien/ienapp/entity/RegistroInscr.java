package com.ien.ienapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "registro_inscr")
public class RegistroInscr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "de_nombre", nullable = false)
    private String nombre;

    @Column(name = "de_apellido", nullable = false)
    private String apellido;

    @Column(name = "de_email", nullable = false)
    private String email;

    @Column(name = "de_fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento; // Cambiado a LocalDate

    @Column(name = "nu_dni", nullable = false)
    private String dni; // Nuevo campo

    @Column(name = "de_direccion")
    private String direccion; // Nuevo campo

    @Column(name = "nu_celular", nullable = false)
    private String nuCelular; // Cambiado

    @Column(name = "nu_telefono")
    private String nuTelefono; // Cambiado

    @Column(name = "de_pais", nullable = false)
    private String pais;

    @Column(name = "de_provincia", nullable = false)
    private String provincia;

    @Column(name = "de_localidad", nullable = false)
    private String localidad;

    @Column(name = "de_carrera", nullable = false)
    private String carrera;

    @Column(name = "de_genero", nullable = false) // Nuevo campo
    private String genero; // Cambiado a String

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    // Setter para 'dni'
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDeDireccion() { // Método getter para 'direccion'
        return direccion;
    }

    public void setDeDireccion(String direccion) { // Método setter para 'direccion'
        this.direccion = direccion;
    }

    public String getNuCelular() { // Método getter para 'nuCelular'
        return nuCelular;
    }

    public void setNuCelular(String nuCelular) { // Método setter para 'nuCelular'
        this.nuCelular = nuCelular;
    }

    public String getNuTelefono() { // Método getter para 'nuTelefono'
        return nuTelefono;
    }

    public void setNuTelefono(String nuTelefono) { // Método setter para 'nuTelefono'
        this.nuTelefono = nuTelefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

