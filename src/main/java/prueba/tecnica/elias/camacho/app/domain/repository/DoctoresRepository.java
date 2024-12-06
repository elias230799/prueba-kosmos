package prueba.tecnica.elias.camacho.app.domain.repository;

import org.springframework.stereotype.Repository;

import prueba.tecnica.elias.camacho.app.domain.entity.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad {@link Doctor}.
 * Proporciona operaciones CRUD para la entidad Doctor.
 * 
 * @see Doctor
 * 
 * @version 1.0.0
 * @date 6-12-2024
 * @autor Elias Camacho Ramirez
 */
@Repository
public interface DoctoresRepository extends JpaRepository<Doctor, Long> {

}
