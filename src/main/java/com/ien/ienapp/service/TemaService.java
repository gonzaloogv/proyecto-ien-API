package com.ien.ienapp.service;

import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.entity.MateriaTema;
import com.ien.ienapp.entity.MateriaTemaId;
import com.ien.ienapp.entity.Tema;
import com.ien.ienapp.repository.MateriaRepository;
import com.ien.ienapp.repository.MateriaTemaRepository;
import com.ien.ienapp.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository; 

    @Autowired
    private MateriaTemaRepository materiaTemaRepository; 

    @Autowired
    private MateriaRepository materiaRepository; 

    public Optional<Tema> getTemaById(Integer id) {
        return temaRepository.findById(id); 
    }
    
    public Tema createTema(Tema tema, Integer idMateria) {
        Tema savedTema = temaRepository.save(tema); 
        if (idMateria != null) {
            Optional<Materia> optionalMateria = materiaRepository.findById(idMateria);
            if (!optionalMateria.isPresent()) {
                throw new IllegalArgumentException("Materia no encontrada con ID: " + idMateria);
            }
    
            // Crear la relación en la tabla intermedia
            MateriaTema materiaTema = new MateriaTema();
            MateriaTemaId id = new MateriaTemaId(optionalMateria.get().getId(), savedTema.getId());
    
            materiaTema.setId(id);
            materiaTema.setTema(savedTema);
            materiaTema.setMateria(optionalMateria.get());
    
            materiaTemaRepository.save(materiaTema); 
        }
    
        return savedTema;
    }    


    public void deleteTema(Integer id) {
        temaRepository.deleteById(id);
    }

    public Iterable<Tema> getAllTemas() {
        return temaRepository.findAll();
    }

    public Tema updateTema(Integer id, Tema temaDetails) {
        Optional<Tema> temaOptional = temaRepository.findById(id);
        if (temaOptional.isPresent()) {
            Tema existingTema = temaOptional.get();
            existingTema.setDeTitulo(temaDetails.getDeTitulo());
            existingTema.setDeDescripcion(temaDetails.getDeDescripcion());
            return temaRepository.save(existingTema);
        } else {
            throw new IllegalArgumentException("Tema no encontrado con ID: " + id);
        }
    }
}
