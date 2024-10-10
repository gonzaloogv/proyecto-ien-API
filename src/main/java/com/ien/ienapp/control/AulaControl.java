
package com.ien.ienapp.control;

import com.ien.ienapp.entity.Aula;
import com.ien.ienapp.service.IAulaService;
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
public class AulaControl {
    
    @Autowired
    private IAulaService interAula;
    
    @GetMapping("/aula/traer")
    public List<Aula> getAula(){
         
        return interAula.getAula();
    
    }
    
    
    @PostMapping("/aula/crear")
    public String crearAula(@RequestBody Aula au){
    
        interAula.saveAula(au);
        return "El Aula fue creada correctamente";
    
    }
    
    @DeleteMapping("/aula/borrar/{id}")
    public String deleteAlumno(@PathVariable Long id){
    
          interAula.deleteAula(id);
          
          return "El Aula fue eliminada correctamente";
    
    
    }
    
    @PutMapping("/aula/editar/{id}")
    public Aula editAula(@PathVariable Long id,
            @RequestParam(required = false, name = "nuCapacidadMax") Integer nuCapacidadMax,
            @RequestParam(required = false, name = "feRegistro") Date feRegistro,
            @RequestParam(required = false, name = "feModificacion") Date feModificacion
            ) {
    
           interAula.editAula(id, nuCapacidadMax, feRegistro, feModificacion);
    
           Aula au = interAula.buscarAula(id);
           
           return au;
    
    }
    
}
