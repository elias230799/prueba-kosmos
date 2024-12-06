package prueba.tecnica.elias.camacho.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prueba.tecnica.elias.camacho.app.domain.entity.Consultorio;
import prueba.tecnica.elias.camacho.app.domain.repository.ConsultoriosRepository;

/**
 * Servicio para gestionar operaciones relacionadas con la entidad
 * {@link Consultorio}.
 * 
 * @see Consultorio
 * @version 1.0.0
 * @date 06-12-2024
 * @autor Elias Camacho Ramirez
 */
@Service
public class ConsultoriosService {

    @Autowired
    private final ConsultoriosRepository repository;

    public ConsultoriosService(ConsultoriosRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todos los consultorios.
     * 
     * @return una lista de todos los consultorios.
     */
    @Transactional(readOnly = true)
    public List<Consultorio> findAll() {
        return repository.findAll();
    }

    /**
     * Obtiene un consultorio por su ID.
     * 
     * @param id el ID del consultorio.
     * @return un {@link Optional} que contiene el consultorio si se encuentra, o
     *         vacío si no.
     */
    @Transactional(readOnly = true)
    public Optional<Consultorio> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Guarda un nuevo consultorio o actualiza uno existente.
     * 
     * @param consultorio el consultorio a guardar.
     * @return el consultorio guardado.
     */
    @Transactional
    public Consultorio save(Consultorio consultorio) {
        try {
            return repository.save(consultorio);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el consultorio", e);
        }
    }

    /**
     * Elimina un consultorio por su ID.
     * 
     * @param id el ID del consultorio a eliminar.
     */
    @Transactional
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el consultorio", e);
        }
    }
}
