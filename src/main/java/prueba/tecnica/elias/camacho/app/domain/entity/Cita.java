package prueba.tecnica.elias.camacho.app.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa una cita médica.
 * 
 * @see Doctor
 * @see Consultorio
 * @author Elias Camacho Ramirez
 * @date 06-12-2024
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "cita", indexes = { @Index(name = "idx_cita_id", columnList = "id") })
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El consultorio no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;

    @NotNull(message = "El doctor no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotNull(message = "El horario de consulta no puede ser nulo")
    @Future(message = "El horario de consulta debe ser una fecha futura")
    @Column(name = "horario_consulta", nullable = false)
    private LocalDateTime horarioConsulta;

    @NotNull(message = "El nombre del paciente no puede ser nulo")
    @NotBlank(message = "El nombre del paciente no puede estar en blanco")
    @Column(name = "nombre_paciente", nullable = false)
    private String nombrePaciente;
}
