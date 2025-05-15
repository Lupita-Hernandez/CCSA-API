package mx.edu.caidt.CCSA_api.Archivo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends CrudRepository<Archivo, Long> {
}
