package prueba.tecnica.elias.camacho.app.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un consultorio.
 * 
 * @see Doctor
 * @see Cita
 * @author Elias Camacho Ramirez
 * @date 06-12-2024
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "consultorio", indexes = { @Index(name = "idx_consultorio_id", columnList = "id") })
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El número del consultorio no puede ser nulo")
    @Min(value = 1, message = "El número del consultorio debe ser un valor positivo")
    @Column(name = "numero", nullable = false)
    private int numero;

    @NotNull(message = "El piso no puede ser nulo")
    @Min(value = 1, message = "El piso debe ser un valor positivo")
    @Column(name = "piso", nullable = false)
    private int piso;
}
