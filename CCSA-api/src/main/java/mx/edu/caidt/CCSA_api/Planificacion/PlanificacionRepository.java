package mx.edu.caidt.CCSA_api.Planificacion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanificacionRepository extends CrudRepository<Planificacion, Long> {
}
