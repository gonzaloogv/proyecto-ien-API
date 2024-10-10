
package com.ien.ienapp.service;

import com.ien.ienapp.entity.Aula;
import com.ien.ienapp.repository.IAulaRepository;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class AulaService implements IAulaService {
    
    @Autowired
    private IAulaRepository aulaRepo;

    @Override
    public List<Aula> getAula() {
    
         List<Aula> listarAulas = aulaRepo.findAll();
        
         return listarAulas;
    
    }

    @Override
    public void saveAula(Aula au) {
        if (au.getFeRegistro() == null) {
            au.setFeRegistro(new Date()); 
        }
        au.setFeModificacion(new Date()); 
        aulaRepo.save(au);
    }


    @Override
    public void deleteAula(Long id) {
          aulaRepo.deleteById(id);    
    }
    

    @Override
    public Aula buscarAula(Long id) {
          
                Aula au = aulaRepo.findById(id).orElse(null);
        
                return au;
    
    }

    @Override
    public void editAula(Long id, Integer nuCapacidadMax, Date feRegistro, Date feModificacion) {

        Aula au = this.buscarAula(id);

   
    if (au != null) {
       
        au.setNuCapacidadMax(nuCapacidadMax);
        au.setFeRegistro(feRegistro);
        au.setFeModificacion(feModificacion);
        
        
        this.saveAula(au);


    }
    else {
        
        throw new EntityNotFoundException("Aula no encontrada con ID: " + id);
    }
    
    
    
    }

   
}
