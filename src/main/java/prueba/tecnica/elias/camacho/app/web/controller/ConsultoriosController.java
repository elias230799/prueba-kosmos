package prueba.tecnica.elias.camacho.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import prueba.tecnica.elias.camacho.app.domain.entity.Consultorio;
import prueba.tecnica.elias.camacho.app.service.ConsultoriosService;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar operaciones relacionadas con la entidad
 * {@link Consultorio}.
 * 
 * @see Consultorio
 * @version 1.0.0
 * @date 06-12-2024
 * @autor Elias Camacho Ramirez
 */
@RestController
@RequestMapping("/api/v1/consultorios")
@Validated
public class ConsultoriosController {

    @Autowired
    private final ConsultoriosService consultoriosService;

    public ConsultoriosController(ConsultoriosService consultoriosService) {
        this.consultoriosService = consultoriosService;
    }

    /**
     * Obtiene todos los consultorios.
     * 
     * @return una lista de todos los consultorios.
     */
    @GetMapping
    public ResponseEntity<List<Consultorio>> getAllConsultorios() {
        List<Consultorio> consultorios = consultoriosService.findAll();
        return new ResponseEntity<>(consultorios, HttpStatus.OK);
    }

    /**
     * Obtiene un consultorio por su ID.
     * 
     * @param id el ID del consultorio.
     * @return el consultorio encontrado, o un estado 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> getConsultorioById(@PathVariable @NotNull Long id) {
        Optional<Consultorio> consultorio = consultoriosService.findById(id);
        return consultorio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Crea un nuevo consultorio.
     * 
     * @param consultorio el consultorio a crear.
     * @return el consultorio creado.
     */
    @PostMapping
    public ResponseEntity<Consultorio> createConsultorio(@Valid @RequestBody Consultorio consultorio) {
        Consultorio nuevoConsultorio = consultoriosService.save(consultorio);
        return new ResponseEntity<>(nuevoConsultorio, HttpStatus.CREATED);
    }

    /**
     * Actualiza un consultorio existente.
     * 
     * @param id          el ID del consultorio a actualizar.
     * @param consultorio el consultorio actualizado.
     * @return el consultorio actualizado, o un estado 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Consultorio> updateConsultorio(@PathVariable @NotNull Long id,
            @Valid @RequestBody Consultorio consultorio) {
        if (!consultoriosService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        consultorio.setId(id);
        Consultorio consultorioActualizado = consultoriosService.save(consultorio);
        return new ResponseEntity<>(consultorioActualizado, HttpStatus.OK);
    }

    /**
     * Elimina un consultorio por su ID.
     * 
     * @param id el ID del consultorio a eliminar.
     * @return un estado 204 si se elimina correctamente, o un estado 404 si no se
     *         encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultorio(@PathVariable @NotNull Long id) {
        if (!consultoriosService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        consultoriosService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
