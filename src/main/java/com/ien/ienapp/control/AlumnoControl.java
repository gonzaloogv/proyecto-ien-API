
package com.ien.ienapp.control;

import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.entity.RRHH;
import com.ien.ienapp.service.IAlumnoService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlumnoControl {
    
    @Autowired
    private IAlumnoService interAlumno;
    
    @GetMapping("/alumno/traer")
    public List<Alumno> getAlumnos(){
         
        return interAlumno.getAlumnos();
    
    }
    
    
    @PostMapping("/alumno/crear")
    public String crearEstudiante(@RequestBody Alumno alum){
    
        interAlumno.saveAlumno(alum);
        return "El alumno fue creado correctamente";
    
    }
    
    @DeleteMapping("/alumno/borrar/{id}")
    public String deleteAlumno(@PathVariable Long id){
    
          interAlumno.deleteAlumno(id);
          
          return "Alumno eliminado correctamente";
    
    
    }
    
    @PutMapping("/alumno/editar/{id}")
    public Alumno editAlumno(@PathVariable Long id,
            @RequestParam(required = false, name = "nuLegajo") String nuLegajo,
            @RequestParam(required = false, name = "nuPromedio") Double nuPromedio,
            @RequestParam(required = false, name = "feIngreso") Date feIngreso,
            @RequestParam(required = false, name = "feEgreso") Date feEgreso,
            @RequestParam(required = false, name = "nuPromedioGral") Double nuPromedioGral,
            @RequestParam(required = false, name = "idPlanEstudio") Integer idPlanEstudio,
            @RequestParam(required = false, name = "tiEstadoInscripcion") String tiEstadoInscripcion,
            @RequestParam(required = false, name = "feRegistro") Date feRegistro,
            @RequestParam(required = false, name = "feModificacion") Date feModificacion,
            @RequestParam(required = false, name = "rrhh") RRHH rrhh
            ) {
        
    
           interAlumno.editAlumno(id, nuLegajo, nuPromedio, feIngreso, feEgreso, nuPromedioGral, idPlanEstudio, tiEstadoInscripcion, feRegistro, feModificacion, rrhh);
    
           Alumno alum = interAlumno.buscarAlumno(id);
           
           return alum;
    
    }
            
          
}
