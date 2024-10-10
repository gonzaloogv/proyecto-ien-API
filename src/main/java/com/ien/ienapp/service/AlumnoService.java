package com.ien.ienapp.service;

import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    // Crear un nuevo alumno
    public Alumno crearAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    // Obtener todos los alumnos
    public List<Alumno> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll();
    }

    // Obtener un alumno por ID
    public Optional<Alumno> obtenerAlumnoPorId(Integer id) {
        return alumnoRepository.findById(id);
    }

    // Actualizar un alumno
    public Alumno actualizarAlumno(Integer id, Alumno alumnoActualizado) {
        if (alumnoRepository.existsById(id)) {
            alumnoActualizado.setId(id);
            return alumnoRepository.save(alumnoActualizado);
        }
        return null; // O lanzar una excepción
    }

    // Eliminar un alumno
    public void eliminarAlumno(Integer id) {
        alumnoRepository.deleteById(id);
    }
}
