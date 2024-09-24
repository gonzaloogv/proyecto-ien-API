
package com.ien.ienapp.service;

import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.entity.RRHH;
import com.ien.ienapp.repository.IAlumnoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService implements IAlumnoService {
    
    @Autowired
    private IAlumnoRepository alumnoRepo;

    @Override
    public List<Alumno> getAlumnos() {
        
         List<Alumno> listaAlumnos = alumnoRepo.findAll();
         
         return listaAlumnos;
        
        
    }

    @Override
    public void saveAlumno(Alumno alum) {
        
        alumnoRepo.save(alum);
        
    }

    @Override
    public void deleteAlumno(Long id) {
        
        alumnoRepo.deleteById(id);
        
    }

    @Override
    public Alumno buscarAlumno(Long id) {
        
        Alumno alum = alumnoRepo.findById(id).orElse(null);
        
        return alum;
        
    }

    @Override
    public void editAlumno(Long id,String nuLegajo, Double nuPromedio, Date feIngreso, Date feEgreso, Double nuPromedioGral, Integer idPlanEstudio, String tiEstadoInscripcion, Date feRegistro, Date feModificacion, RRHH rrhh) {
    Alumno alum = this.buscarAlumno(id);

   
    if (alum != null) {
       
        alum.setNuLegajo(nuLegajo);
        alum.setNuPromedio(nuPromedio);
        alum.setFeIngreso(feIngreso);
        alum.setFeEgreso(feEgreso);
        alum.setNuPromedioGral(nuPromedioGral);
        alum.setIdPlanEstudio(idPlanEstudio);
        alum.setTiEstadoInscripcion(tiEstadoInscripcion);
        alum.setFeRegistro(feRegistro);
        alum.setFeModificacion(feModificacion);
        alum.setFeModificacion(feModificacion);
        alum.setRrhh(rrhh);
        
        this.saveAlumno(alum);


    }
    else {
        
        throw new EntityNotFoundException("Alumno no encontrado con ID: " + id);
    }
    
    }
    
}
