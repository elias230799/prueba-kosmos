package prueba.tecnica.elias.camacho.app.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa a un doctor.
 * Extiende de la clase {@link Persona}.
 * 
 * @see Persona
 * @author Elias Camacho Ramirez
 * @date 06-12-2024
 * @version 1.0.0
 */

@Getter
@Setter
@Entity
@Table(name = "doctor", indexes = { @Index(name = "idx_doctor_id", columnList = "id") })
public class Doctor extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La especialidad no puede ser nula")
    @NotBlank(message = "La especialidad no puede estar en blanco")
    @Column(name = "especialidad", nullable = false)
    private String especialidad;
}
