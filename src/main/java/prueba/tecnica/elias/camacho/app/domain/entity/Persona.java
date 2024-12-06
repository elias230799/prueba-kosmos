package prueba.tecnica.elias.camacho.app.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Clase base para representar una persona.
 * Esta clase es una superclase mapeada que puede ser extendida por otras
 * entidades.
 * 
 * @see Doctor
 * @author Elias Camacho Ramirez
 * @date 06-12-2024
 * @version 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull(message = "El apellido paterno no puede ser nulo")
    @NotBlank(message = "El apellido paterno no puede estar en blanco")
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @NotNull(message = "El apellido materno no puede ser nulo")
    @NotBlank(message = "El apellido materno no puede estar en blanco")
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

}
