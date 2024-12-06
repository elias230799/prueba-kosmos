package prueba.tecnica.elias.camacho.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        validarCita(cita);
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

    /**
     * Valida las reglas de negocio para la creación y actualización de citas.
     * 
     * @param cita la cita a validar.
     */
    private void validarCita(Cita cita) {
        LocalDateTime inicio = cita.getHorarioConsulta();
        LocalDateTime fin = inicio.plusHours(1);

        // No se puede agendar cita en un mismo consultorio a la misma hora.
        if (repository.existsByConsultorioAndHorarioConsultaBetween(cita.getConsultorio(), inicio, fin)) {
            throw new IllegalArgumentException("No se puede agendar cita en el mismo consultorio a la misma hora.");
        }

        // No se puede agendar cita para un mismo Dr. a la misma hora.
        if (repository.existsByDoctorAndHorarioConsultaBetween(cita.getDoctor(), inicio, fin)) {
            throw new IllegalArgumentException("No se puede agendar cita para el mismo Dr. a la misma hora.");
        }

        // No se puede agendar cita para un paciente a la misma hora ni con menos de 2
        // horas de diferencia para el mismo día.
        LocalDateTime inicioDia = inicio.truncatedTo(ChronoUnit.DAYS);
        LocalDateTime finDia = inicioDia.plusDays(1);
        if (repository.existsByNombrePacienteAndHorarioConsultaBetween(cita.getNombrePaciente(), inicioDia, finDia)) {
            List<Cita> citasPaciente = repository
                    .findByNombrePacienteAndHorarioConsultaBetween(cita.getNombrePaciente(), inicioDia, finDia);
            for (Cita c : citasPaciente) {
                if (ChronoUnit.HOURS.between(c.getHorarioConsulta(), inicio) < 2) {
                    throw new IllegalArgumentException(
                            "No se puede agendar cita para un paciente con menos de 2 horas de diferencia para el mismo día.");
                }
            }
        }

        // Un mismo doctor no puede tener más de 8 citas en el día.
        if (repository.countByDoctorAndHorarioConsultaBetween(cita.getDoctor(), inicioDia, finDia) >= 8) {
            throw new IllegalArgumentException("Un mismo doctor no puede tener más de 8 citas en el día.");
        }
    }

    /**
     * Busca citas por fecha, consultorio y doctor.
     * 
     * @param fecha         la fecha de las citas.
     * @param consultorioId el ID del consultorio de las citas.
     * @param doctorId      el ID del doctor de las citas.
     * @return una lista de citas que coinciden con los criterios de búsqueda.
     */
    @Transactional(readOnly = true)
    public List<Cita> buscarCitas(LocalDateTime fecha, Long consultorioId, Long doctorId) {
        try {
            List<Cita> citas;
            if (fecha != null) {
                LocalDateTime inicioDia = fecha.truncatedTo(ChronoUnit.DAYS);
                LocalDateTime finDia = inicioDia.plusDays(1);
                if (consultorioId != null) {
                    citas = repository.findByConsultorioIdAndHorarioConsultaBetween(consultorioId, inicioDia, finDia);
                } else if (doctorId != null) {
                    citas = repository.findByDoctorIdAndHorarioConsultaBetween(doctorId, inicioDia, finDia);
                } else {
                    citas = repository.findByHorarioConsultaBetween(inicioDia, finDia);
                }
            } else {
                citas = repository.findAll();
            }
            return citas;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar citas", e);
        }
    }
}