package prueba.tecnica.elias.camacho.app.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prueba.tecnica.elias.camacho.app.domain.entity.Cita;
import prueba.tecnica.elias.camacho.app.domain.entity.Consultorio;
import prueba.tecnica.elias.camacho.app.domain.entity.Doctor;

/**
 * Repositorio para la entidad {@link Cita}.
 * Proporciona operaciones CRUD para la entidad Cita.
 * 
 * @see Cita
 * 
 * @version 1.0.0
 * @date 6-12-2024
 * @autor Elias Camacho Ramirez
 */
@Repository
public interface CitasRepository extends JpaRepository<Cita, Long> {
    boolean existsByConsultorioAndHorarioConsultaBetween(Consultorio consultorio, LocalDateTime inicio,
            LocalDateTime fin);

    boolean existsByDoctorAndHorarioConsultaBetween(Doctor doctor, LocalDateTime inicio, LocalDateTime fin);

    boolean existsByNombrePacienteAndHorarioConsultaBetween(String nombrePaciente, LocalDateTime inicio,
            LocalDateTime fin);

    List<Cita> findByNombrePacienteAndHorarioConsultaBetween(String nombrePaciente, LocalDateTime inicio,
            LocalDateTime fin);

    long countByDoctorAndHorarioConsultaBetween(Doctor doctor, LocalDateTime inicio, LocalDateTime fin);

    List<Cita> findByHorarioConsultaBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Cita> findByConsultorioIdAndHorarioConsultaBetween(Long consultorioId, LocalDateTime inicio,
            LocalDateTime fin);

    List<Cita> findByDoctorIdAndHorarioConsultaBetween(Long doctorId, LocalDateTime inicio, LocalDateTime fin);
}
