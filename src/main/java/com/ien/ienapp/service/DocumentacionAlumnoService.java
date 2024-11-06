package com.ien.ienapp.service;

import com.ien.ienapp.dto.DocumentacionDTO;
import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.entity.DocumentacionAlumno;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.AlumnoRepository;
import com.ien.ienapp.repository.DocumentacionAlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentacionAlumnoService {

    @Autowired
    private DocumentacionAlumnoRepository documentacionAlumnoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public DocumentacionAlumnoService(DocumentacionAlumnoRepository documentacionAlumnoRepository) {
        this.documentacionAlumnoRepository = documentacionAlumnoRepository;
    }

    public DocumentacionAlumno crearDocumentacionAlumno(DocumentacionDTO documentacionDTO) {
        DocumentacionAlumno documentacionAlumno = new DocumentacionAlumno();
        
        // Buscar la carrera por ID y asociarla
        Alumno alumno = alumnoRepository.findById(documentacionDTO.getIdAlumno())
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));
        
        documentacionAlumno.setAlumno(alumno);
        documentacionAlumno.setDeDescripcion(documentacionDTO.getDeDescripcion());
        documentacionAlumno.setFeRegistro(documentacionDTO.getFeRegistro());
        documentacionAlumno.setFeModificacion(documentacionDTO.getFeModificacion());

        return documentacionAlumnoRepository.save(documentacionAlumno);
    }
    
    public List<DocumentacionDTO> obtenerDocumentacion() {
        return documentacionAlumnoRepository.findAll().stream()
                .map(this::convertirDocumentacionAlumnoDTO)
                .collect(Collectors.toList());
    }

    public DocumentacionDTO obtenerDocumentacionAlumnoPorId(Integer id) {
        return documentacionAlumnoRepository.findById(id)
                .map(this::convertirDocumentacionAlumnoDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Documentacion no encontrada"));
    }    
        
    public DocumentacionAlumno actualizarDocumentacionAlumno(Integer id, DocumentacionDTO documentacionDTO) {
        if (documentacionAlumnoRepository.existsById(id)) {
            DocumentacionAlumno documentacionAlumno = documentacionAlumnoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Documentacion no encontrada"));
    
            // Aquí asignas los valores correctos del DTO a la entidad
            documentacionAlumno.setDeDescripcion(documentacionDTO.getDeDescripcion());
            documentacionAlumno.setFeModificacion(documentacionDTO.getFeModificacion());
            
            // Para actualizar la carrera también, si corresponde
            if (documentacionDTO.getIdAlumno() != null) {
                Alumno alumno = alumnoRepository.findById(documentacionDTO.getIdAlumno())
                    .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado"));
                documentacionAlumno.setAlumno(alumno);
            }
    
            return documentacionAlumnoRepository.save(documentacionAlumno);
        }
        throw new ResourceNotFoundException("Documentacion no encontrada");
    }   

    public void eliminarDocumentacion(Integer id) {
        if (documentacionAlumnoRepository.existsById(id)) {
            documentacionAlumnoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Documentacion no encontrada");
        }
    }

    private DocumentacionDTO convertirDocumentacionAlumnoDTO(DocumentacionAlumno documentacionAlumno) {
        DocumentacionDTO dto = new DocumentacionDTO();
        dto.setIdDocumentacion(documentacionAlumno.getId());
        if (documentacionAlumno.getAlumno() != null) {
            dto.setIdCarrera(documentacionAlumno.getAlumno().getId());
        }
        dto.setDeDescripcion(documentacionAlumno.getDeDescripcion());
        dto.setFeRegistro(documentacionAlumno.getFeRegistro());
        dto.setFeModificacion(documentacionAlumno.getFeModificacion());
        return dto;
    }
}
