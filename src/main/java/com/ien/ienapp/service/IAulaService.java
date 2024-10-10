package com.ien.ienapp.service;

import com.ien.ienapp.entity.Aula;
import java.util.Date;
import java.util.List;



public interface IAulaService {
    
    
    public List<Aula> getAula();
    
    public void saveAula(Aula au);
    
    public void deleteAula(Long id);
    
    public Aula buscarAula(Long id);
    
    public void editAula(Long id,
                            Integer nuCapacidadMax,
                            Date feRegistro, 
                            Date feModificacion );


    
}
