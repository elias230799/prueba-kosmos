package prueba.tecnica.elias.camacho.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prueba.tecnica.elias.camacho.app.domain.entity.Consultorio;

/**
 * Repositorio para la entidad {@link Consultorio}.
 * Proporciona operaciones CRUD para la entidad Consultorio.
 * 
 * @see Consultorio
 * 
 * @version 1.0.0
 * @date 6-12-2024
 * @autor Elias Camacho Ramirez
 */
@Repository
public interface ConsultoriosRepository extends JpaRepository<Consultorio, Long> {

}
