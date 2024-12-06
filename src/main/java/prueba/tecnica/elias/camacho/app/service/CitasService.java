package prueba.tecnica.elias.camacho.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prueba.tecnica.elias.camacho.app.domain.entity.Cita;
import prueba.tecnica.elias.camacho.app.domain.repository.CitasRepository;

/**
 * Servicio para gestionar operaciones relacionadas con la entidad {@link Cita}.
 * 
 * @see Cita
 * @version 1.0.0
 * @date 06-12-2024
 * @autor Elias Camacho Ramirez
 */
@Service
public class CitasService {

    @Autowired
    private final CitasRepository repository;

    public CitasService(CitasRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todas las citas.
     * 
     * @return una lista de todas las citas.
     */
    @Transactional(readOnly = true)
    public List<Cita> findAll() {
        return repository.findAll();
    }

    /**
     * Obtiene una cita por su ID.
     * 
     * @param id el ID de la cita.
     * @return un {@link Optional} que contiene la cita si se encuentra, o vacío si
     *         no.
     */
    @Transactional(readOnly = true)
    public Optional<Cita> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Guarda una nueva cita o actualiza una existente.
     * 
     * @param cita la cita a guardar.
     * @return la cita guardada.
     */
    @Transactional
    public Cita save(Cita cita) {
        try {
            return repository.save(cita);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la cita", e);
        }
    }

    /**
     * Elimina una cita por su ID.
     * 
     * @param id el ID de la cita a eliminar.
     */
    @Transactional
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la cita", e);
        }
    }
}