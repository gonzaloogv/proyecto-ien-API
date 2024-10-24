package com.ien.ienapp.control;

import com.ien.ienapp.dto.RRHHDTO;
import com.ien.ienapp.entity.RRHH;
import com.ien.ienapp.service.RRHHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/rrhh")
public class RRHHController {

    @Autowired
    private RRHHService rrhhService;

    @GetMapping
    public List<RRHHDTO> getAllRRHH() {
        List<RRHH> rrhhList = rrhhService.getAllRRHH();
        return rrhhList.stream()
                .map(rrhh -> rrhhService.convertToDTO(rrhh))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RRHHDTO> getRRHHById(@PathVariable Integer id) {
        Optional<RRHH> rrhh = rrhhService.getRRHHById(id);
        if (rrhh.isPresent()) {
            return ResponseEntity.ok(rrhhService.convertToDTO(rrhh.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createRRHH(@Valid @RequestBody RRHHDTO rrhhDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage()) // Obtiene el mensaje de cada error
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }

        RRHH rrhh = rrhhService.convertToEntity(rrhhDTO);

        try {
            RRHH newRRHH = rrhhService.createRRHH(rrhh);
            return ResponseEntity.ok(rrhhService.convertToDTO(newRRHH));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear RRHH: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRRHH(@PathVariable Integer id) {
        rrhhService.deleteRRHH(id);
    }
}
