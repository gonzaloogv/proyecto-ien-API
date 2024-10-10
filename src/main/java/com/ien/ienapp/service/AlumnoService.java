package com.ien.ienapp.service;

import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.repository.AlumnoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> getAlumnoById(Long id) {
        return alumnoRepository.findById(id);
    }

    public Alumno createAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno updateAlumno(Long id, Alumno updatedAlumno) {
        return alumnoRepository.findById(id).map(alumno -> {
            alumno.setFeEgreso(updatedAlumno.getFeEgreso());
            alumno.setFeIngreso(updatedAlumno.getFeIngreso());
            alumno.setFeModificacion(updatedAlumno.getFeModificacion());
            alumno.setFeRegistro(updatedAlumno.getFeRegistro());
            alumno.setIdPlanEstudio(updatedAlumno.getIdPlanEstudio());
            alumno.setNuLegajo(updatedAlumno.getNuLegajo());
            alumno.setNuPromedio(updatedAlumno.getNuPromedio());
            alumno.setNuPromedioGral(updatedAlumno.getNuPromedioGral());
            alumno.setTiEstadoInscripcion(updatedAlumno.getTiEstadoInscripcion());
            alumno.setFkAlRrhh(updatedAlumno.getFkAlRrhh());
            alumno.setIdRol(updatedAlumno.getIdRol());
            return alumnoRepository.save(alumno);
        }).orElseGet(() -> {
            updatedAlumno.setId(id);
            return alumnoRepository.save(updatedAlumno);
        });
    }

    public void deleteAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }
}
