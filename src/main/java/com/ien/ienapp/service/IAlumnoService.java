package com.ien.ienapp.service;

import com.ien.ienapp.entity.Alumno;
import java.util.Date;
import java.util.List;



public interface IAlumnoService {
    
    public List<Alumno> getAlumnos();
    
    public void saveAlumno(Alumno alum);
    
    public void deleteAlumno(Long id);
    
    public Alumno buscarAlumno(Long id);
    
    public void editAlumno(Long id,
                            String nuLegajo,
                            Double nuPromedio, 
                            Date feIngreso, 
                            Date feEgreso, 
                            Double nuPromedioGral, 
                            Integer idPlanEstudio, 
                            String tiEstadoInscripcion, 
                            Date feRegistro, 
                            Date feModificacion  );
    
}
