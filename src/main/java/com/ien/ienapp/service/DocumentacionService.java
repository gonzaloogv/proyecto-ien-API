package com.ien.ienapp.service;

import com.ien.ienapp.dto.DocumentacionDTO;
import com.ien.ienapp.entity.Carrera;
import com.ien.ienapp.entity.Documentacion;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.CarreraRepository;
import com.ien.ienapp.repository.DocumentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentacionService {

    @Autowired
    private DocumentacionRepository documentacionRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    public DocumentacionService(DocumentacionRepository documentacionRepository) {
        this.documentacionRepository = documentacionRepository;
    }

    public Documentacion crearDocumentacion(DocumentacionDTO documentacionDTO) {
        Documentacion documentacion = new Documentacion();
        
        // Buscar la carrera por ID y asociarla
        Carrera carrera = carreraRepository.findById(documentacionDTO.getIdCarrera())
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));
        
        documentacion.setCarrera(carrera);
        documentacion.setDeDescripcion(documentacionDTO.getDeDescripcion());
        documentacion.setFeRegistro(documentacionDTO.getFeRegistro());
        documentacion.setFeModificacion(documentacionDTO.getFeModificacion());

        return documentacionRepository.save(documentacion);
    }
    
    public List<DocumentacionDTO> obtenerDocumentacion() {
        return documentacionRepository.findAll().stream()
                .map(this::convertirDocumentacionDTO)
                .collect(Collectors.toList());
    }

    public DocumentacionDTO obtenerDocumentacionPorId(Integer id) {
        return documentacionRepository.findById(id)
                .map(this::convertirDocumentacionDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Documentacion no encontrada"));
    }    
        
    public Documentacion actualizarDocumentacion(Integer id, DocumentacionDTO documentacionDTO) {
        if (documentacionRepository.existsById(id)) {
            Documentacion documentacion = documentacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Documentacion no encontrada"));
    
            // Aquí asignas los valores correctos del DTO a la entidad
            documentacion.setDeDescripcion(documentacionDTO.getDeDescripcion());
            documentacion.setFeModificacion(documentacionDTO.getFeModificacion());
            
            // Para actualizar la carrera también, si corresponde
            if (documentacionDTO.getIdCarrera() != null) {
                Carrera carrera = carreraRepository.findById(documentacionDTO.getIdCarrera())
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));
                documentacion.setCarrera(carrera);
            }
    
            return documentacionRepository.save(documentacion);
        }
        throw new ResourceNotFoundException("Documentacion no encontrada");
    }
      

    public void eliminarDocumentacion(Integer id) {
        if (documentacionRepository.existsById(id)) {
            documentacionRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Documentacion no encontrada");
        }
    }

    private DocumentacionDTO convertirDocumentacionDTO(Documentacion documentacion) {
        DocumentacionDTO dto = new DocumentacionDTO();
        dto.setIdDocumentacion(documentacion.getId());
        if (documentacion.getCarrera() != null) {
            dto.setIdCarrera(documentacion.getCarrera().getId());
        }
        dto.setDeDescripcion(documentacion.getDeDescripcion());
        dto.setFeRegistro(documentacion.getFeRegistro());
        dto.setFeModificacion(documentacion.getFeModificacion());
        return dto;
    }
}
