package mx.edu.caidt.CCSA_api.Componente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponenteRepository extends CrudRepository<Componente, Long> {
}
