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
    private TemaRepository temaRepository; // Repositorio para manejar los temas

    @Autowired
    private MateriaTemaRepository materiaTemaRepository; // Repositorio para manejar la tabla intermedia

    @Autowired
    private MateriaRepository materiaRepository; // Repositorio para manejar las materias

    public Optional<Tema> getTemaById(Integer id) {
        return temaRepository.findById(id); // Utiliza el repositorio para buscar por ID
    }
    
    // Método para crear un nuevo tema y la relación con la materia
    public Tema createTema(Tema tema, Integer idMateria) {
        Tema savedTema = temaRepository.save(tema); // Guardar el tema
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
    
            materiaTemaRepository.save(materiaTema); // Guardar la relación
        }
    
        return savedTema;
    }    

    // Método para eliminar un tema por su ID
    public void deleteTema(Integer id) {
        temaRepository.deleteById(id);
    }

    // Método para listar todos los temas
    public Iterable<Tema> getAllTemas() {
        return temaRepository.findAll();
    }

    // Método para actualizar un tema por su ID
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
