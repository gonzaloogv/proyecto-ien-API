package com.ien.ienapp.service;

import com.ien.ienapp.dto.TemaDTO;
import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.entity.MateriaTema;
import com.ien.ienapp.entity.MateriaTemaId;
import com.ien.ienapp.entity.Tema;
import com.ien.ienapp.repository.MateriaRepository;
import com.ien.ienapp.repository.MateriaTemaRepository;
import com.ien.ienapp.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaTemaService {

    @Autowired
    private MateriaTemaRepository materiaTemaRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private TemaRepository temaRepository;

    public void crearRelacion(Integer idMateria, Integer idTema, Date feRegistro) {
        // Buscar la materia en la base de datos
        Optional<Materia> materiaOptional = materiaRepository.findById(idMateria);
        if (!materiaOptional.isPresent()) {
            throw new IllegalArgumentException("Materia con ID " + idMateria + " no encontrada.");
        }

        // Buscar el tema en la base de datos
        Optional<Tema> temaOptional = temaRepository.findById(idTema);
        if (!temaOptional.isPresent()) {
            throw new IllegalArgumentException("Tema con ID " + idTema + " no encontrado.");
        }

        // Crear la entidad MateriaTema con las referencias de Materia y Tema
        MateriaTemaId materiaTemaId = new MateriaTemaId(idMateria, idTema);
        MateriaTema materiaTema = new MateriaTema();
        materiaTema.setId(materiaTemaId);
        materiaTema.setMateria(materiaOptional.get()); // Asignar la materia
        materiaTema.setTema(temaOptional.get()); // Asignar el tema
        materiaTema.setFeRegistro(feRegistro);

        // Guardar la relación en la tabla intermedia
        materiaTemaRepository.save(materiaTema);
    }
    public List<MateriaTema> getAllRelaciones() {
        return materiaTemaRepository.findAll(); // Asegúrate de que el repositorio tenga este método
    }

    public List<TemaDTO> getTemasByMateriaId(Integer idMateria) {
        List<MateriaTema> materiaTemas = materiaTemaRepository.findByMateria_Id(idMateria);
        
        return materiaTemas.stream()
                           .map(materiaTema -> {
                               Tema tema = materiaTema.getTema();
                               return new TemaDTO(tema.getId(), tema.getDeTitulo(), tema.getDeDescripcion());
                           })
                           .collect(Collectors.toList());
    }
}

