package prueba.tecnica.elias.camacho.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import prueba.tecnica.elias.camacho.app.domain.entity.Doctor;
import prueba.tecnica.elias.camacho.app.service.DoctoresService;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar operaciones relacionadas con la entidad
 * {@link Doctor}.
 * 
 * @see Doctor
 * @version 1.0.0
 * @date 06-12-2024
 * @autor Elias Camacho Ramirez
 */
@RestController
@RequestMapping("/api/v1/doctores")
@Validated
public class DoctoresController {

    @Autowired
    private final DoctoresService doctorService;

    public DoctoresController(DoctoresService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * Obtiene todos los doctores.
     * 
     * @return una lista de todos los doctores.
     */
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctores() {
        List<Doctor> doctores = doctorService.findAll();
        return new ResponseEntity<>(doctores, HttpStatus.OK);
    }

    /**
     * Obtiene un doctor por su ID.
     * 
     * @param id el ID del doctor.
     * @return el doctor encontrado, o un estado 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable @NotNull Long id) {
        Optional<Doctor> doctor = doctorService.findById(id);
        return doctor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Crea un nuevo doctor.
     * 
     * @param doctor el doctor a crear.
     * @return el doctor creado.
     */
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor nuevoDoctor = doctorService.save(doctor);
        return new ResponseEntity<>(nuevoDoctor, HttpStatus.CREATED);
    }

    /**
     * Actualiza un doctor existente.
     * 
     * @param id     el ID del doctor a actualizar.
     * @param doctor el doctor actualizado.
     * @return el doctor actualizado, o un estado 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable @NotNull Long id, @Valid @RequestBody Doctor doctor) {
        if (!doctorService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        doctor.setId(id);
        Doctor doctorActualizado = doctorService.save(doctor);
        return new ResponseEntity<>(doctorActualizado, HttpStatus.OK);
    }

    /**
     * Elimina un doctor por su ID.
     * 
     * @param id el ID del doctor a eliminar.
     * @return un estado 204 si se elimina correctamente, o un estado 404 si no se
     *         encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable @NotNull Long id) {
        if (!doctorService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        doctorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}