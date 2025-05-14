package mx.edu.caidt.CCSA_api.UnidadResponsable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/unidadResponsable")
public class UnidadResponsableController {

    @Autowired
    private UnidadResponsableRepository unidadResponsableRepository;

    @GetMapping()
    public ResponseEntity<Iterable<UnidadResponsable>> findAll() {
        return ResponseEntity.ok(unidadResponsableRepository.findAll());
    }

    @GetMapping("/{idUnidadResponsable}")
    public ResponseEntity<UnidadResponsable> findById(@PathVariable Long idUnidadResponsable) {
        Optional<UnidadResponsable> unidadResponsableOptional = unidadResponsableRepository.findById(idUnidadResponsable);
        if (unidadResponsableOptional.isPresent()) {
            return ResponseEntity.ok(unidadResponsableOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UnidadResponsable> create(@RequestBody UnidadResponsable unidadResponsable, UriComponentsBuilder ucBuilder) {
        UnidadResponsable created = unidadResponsableRepository.save(unidadResponsable);
        URI uri = ucBuilder.path("/unidadResponsable/{idUnidadResponsable}").buildAndExpand(created.getIdUnidadResponsable()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idUnidadResponsable}")
    public ResponseEntity<Void> update(@PathVariable Long idUnidadResponsable, @RequestBody UnidadResponsable unidadResponsable) {
        UnidadResponsable unidadResponsableAnterior = unidadResponsableRepository.findById(idUnidadResponsable).get();
        if (unidadResponsableAnterior != null) {

            unidadResponsable.setIdUnidadResponsable(unidadResponsableAnterior.getIdUnidadResponsable());
            unidadResponsableRepository.save(unidadResponsable);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
