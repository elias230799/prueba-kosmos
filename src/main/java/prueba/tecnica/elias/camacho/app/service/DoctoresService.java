package prueba.tecnica.elias.camacho.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prueba.tecnica.elias.camacho.app.domain.entity.Doctor;
import prueba.tecnica.elias.camacho.app.domain.repository.DoctoresRepository;

/**
 * Servicio para gestionar operaciones relacionadas con la entidad
 * {@link Doctor}.
 * 
 * @see Doctor
 * @version 1.0.0
 * @date 06-12-2024
 * @autor Elias Camacho Ramirez
 */
@Service
public class DoctoresService {

    @Autowired
    private DoctoresRepository repository;

    DoctoresService(DoctoresRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todos los doctores.
     * 
     * @return una lista de todos los doctores.
     */
    @Transactional(readOnly = true)
    public List<Doctor> findAll() {
        return repository.findAll();
    }

    /**
     * Obtiene un doctor por su ID.
     * 
     * @param id el ID del doctor.
     * @return un {@link Optional} que contiene el doctor si se encuentra, o vacío
     *         si no.
     */
    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Guarda un nuevo doctor o actualiza uno existente.
     * 
     * @param doctor el doctor a guardar.
     * @return el doctor guardado.
     */
    @Transactional
    public Doctor save(Doctor doctor) {
        try {
            return repository.save(doctor);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el doctor", e);
        }
    }

    /**
     * Elimina un doctor por su ID.
     * 
     * @param id el ID del doctor a eliminar.
     */
    @Transactional
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el doctor", e);
        }
    }
}
