package com.ien.ienapp.service;

import com.ien.ienapp.dto.MateriaDTO;
import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.entity.Carrera;
import com.ien.ienapp.repository.MateriaRepository;
import com.ien.ienapp.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CarreraRepository carreraRepository; // Repositorio para Carrera

    // Crear una nueva materia
    @Transactional
    public MateriaDTO crearMateria(MateriaDTO materiaDTO) {
        Materia materia = new Materia();
        materia.setDeNombre(materiaDTO.getDeNombre());
        materia.setTaAsistenciaObligatoria(materiaDTO.getTaAsistenciaObligatoria());
        materia.setFeRegistro(new Date());

        // Manejo del ID de Carrera
        if (materiaDTO.getIdCarrera() != null) {
            Optional<Carrera> carrera = carreraRepository.findById(materiaDTO.getIdCarrera());
            if (carrera.isPresent()) {
                materia.setCarrera(carrera.get());
            } else {
                throw new RuntimeException("Carrera no encontrada");
            }
        }

        // Guardar la materia
        Materia nuevaMateria = materiaRepository.save(materia);

        // Convertir a DTO y agregar el id
        MateriaDTO resultDTO = new MateriaDTO();
        resultDTO.setId(nuevaMateria.getId()); // Establecer el ID
        resultDTO.setDeNombre(nuevaMateria.getDeNombre());
        resultDTO.setTaAsistenciaObligatoria(nuevaMateria.getTaAsistenciaObligatoria());
        resultDTO.setFeRegistro(nuevaMateria.getFeRegistro());
        resultDTO.setIdCarrera(nuevaMateria.getCarrera() != null ? nuevaMateria.getCarrera().getId() : null); // Agregar idCarrera al DTO

        return resultDTO;
    }

    // Obtener todas las materias
    public List<MateriaDTO> getAllMaterias() {
        List<Materia> materias = materiaRepository.findAll();
        return materias.stream()
                .map(this::convertirAMateriaDTO) // Usando el método convertirAMateriaDTO
                .collect(Collectors.toList());
    }
    public List<MateriaDTO> getMateriasByCarreraId(Integer carreraId) {
        List<Materia> materias = materiaRepository.findByCarreraId(carreraId);
        
        return materias.stream()
                       .map(this::convertirAMateriaDTO) // Usando el método convertirAMateriaDTO
                       .collect(Collectors.toList());
    }
    // Obtener una materia por ID
    public Optional<Materia> obtenerMateriaPorId(Integer id) {
        return materiaRepository.findById(id);
    }

    // Método para convertir Materia a MateriaDTO
    private MateriaDTO convertirAMateriaDTO(Materia materia) {
        MateriaDTO dto = new MateriaDTO();
        dto.setId(materia.getId()); // Establecer el ID
        dto.setDeNombre(materia.getDeNombre());
        dto.setTaAsistenciaObligatoria(materia.getTaAsistenciaObligatoria());
        dto.setIdCarrera(materia.getCarrera() != null ? materia.getCarrera().getId() : null); // Cambiado a getCarrera().getId()
        dto.setFeRegistro(materia.getFeRegistro());
        return dto;
    }

    // Actualizar una materia
    @Transactional
    public MateriaDTO actualizarMateria(Integer id, MateriaDTO materiaDTO) {
        Optional<Materia> optionalMateria = materiaRepository.findById(id);
        if (optionalMateria.isPresent()) {
            Materia materia = optionalMateria.get();
            materia.setDeNombre(materiaDTO.getDeNombre());
            materia.setTaAsistenciaObligatoria(materiaDTO.getTaAsistenciaObligatoria());
            materia.setFeModificacion(new Date());

            // Manejo de Carrera por ID
            if (materiaDTO.getIdCarrera() != null) {
                Optional<Carrera> carrera = carreraRepository.findById(materiaDTO.getIdCarrera());
                carrera.ifPresent(materia::setCarrera); // Asociar la Carrera a la Materia
            }

            materiaRepository.save(materia);
            
            // Convertir a DTO y devolver el id
            MateriaDTO resultDTO = new MateriaDTO();
            resultDTO.setId(materia.getId()); // Establecer el ID
            resultDTO.setDeNombre(materia.getDeNombre());
            resultDTO.setTaAsistenciaObligatoria(materia.getTaAsistenciaObligatoria());
            resultDTO.setFeRegistro(materia.getFeRegistro());
            resultDTO.setIdCarrera(materia.getCarrera() != null ? materia.getCarrera().getId() : null); // Agregar idCarrera al DTO

            return resultDTO;
        }
        return null; // o lanzar una excepción si no se encuentra
    }

    @Transactional
    public boolean eliminarMateria(Integer id) {
        Optional<Materia> materiaOptional = materiaRepository.findById(id);
        if (materiaOptional.isPresent()) {
            materiaRepository.delete(materiaOptional.get());
            return true; 
        }
        return false; 
    }
}
