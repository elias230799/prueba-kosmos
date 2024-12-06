package prueba.tecnica.elias.camacho.app.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import prueba.tecnica.elias.camacho.app.domain.entity.Cita;
import prueba.tecnica.elias.camacho.app.service.CitasService;

/**
 * Controlador REST para gestionar operaciones relacionadas con la entidad
 * {@link Cita}.
 * 
 * @see Cita
 * @version 1.0.0
 * @date 06-12-2024
 * @autor Elias Camacho Ramirez
 */
@RestController
@RequestMapping("/api/v1/citas")
@Validated
public class CitasController {

    @Autowired
    private final CitasService citasService;

    public CitasController(CitasService citasService) {
        this.citasService = citasService;
    }

    /**
     * Obtiene todas las citas.
     * 
     * @return una lista de todas las citas.
     */
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        List<Cita> citas = citasService.findAll();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    /**
     * Obtiene una cita por su ID.
     * 
     * @param id el ID de la cita.
     * @return la cita encontrada, o un estado 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable @NotNull Long id) {
        Optional<Cita> cita = citasService.findById(id);
        return cita.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Crea una nueva cita.
     * 
     * @param cita la cita a crear.
     * @return la cita creada.
     */
    @PostMapping
    public ResponseEntity<Cita> createCita(@Valid @RequestBody Cita cita) {
        Cita nuevaCita = citasService.save(cita);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }

    /**
     * Actualiza una cita existente.
     * 
     * @param id   el ID de la cita a actualizar.
     * @param cita la cita actualizada.
     * @return la cita actualizada, o un estado 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable @NotNull Long id, @Valid @RequestBody Cita cita) {
        if (!citasService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cita.setId(id);
        Cita citaActualizada = citasService.save(cita);
        return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
    }

    /**
     * Elimina una cita por su ID.
     * 
     * @param id el ID de la cita a eliminar.
     * @return un estado 204 si se elimina correctamente, o un estado 404 si no se
     *         encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable @NotNull Long id) {
        if (!citasService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        citasService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
