package mx.edu.caidt.CCSA_api.Actividades;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends CrudRepository<Actividad, Long> {
}
