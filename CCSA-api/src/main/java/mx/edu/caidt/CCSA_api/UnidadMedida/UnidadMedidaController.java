package mx.edu.caidt.CCSA_api.UnidadMedida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/unidadMedida")
public class UnidadMedidaController {

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    @GetMapping()
    public ResponseEntity<Iterable<UnidadMedida>> findAll() {
        return ResponseEntity.ok(unidadMedidaRepository.findAll());
    }

    @GetMapping("/{idUnidadMedida}")
    public ResponseEntity<UnidadMedida> findById(@PathVariable Long idUnidadMedida) {
        Optional<UnidadMedida> unidadMedidaOptional = unidadMedidaRepository.findById(idUnidadMedida);
        if (unidadMedidaOptional.isPresent()) {
            return ResponseEntity.ok(unidadMedidaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UnidadMedida newUnidadMedida, UriComponentsBuilder ucb) {
        UnidadMedida saveUnidadMedida = unidadMedidaRepository.save(newUnidadMedida);
        URI uri = ucb
                .path("unidadMedida/{idUnidadMedida}")
                .buildAndExpand(saveUnidadMedida.getIdUnidadMedida())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idUnidadMedida}")
    public ResponseEntity<Void> update(@PathVariable Long idUnidadMedida, @RequestBody UnidadMedida unidadMedidaAct) {
        UnidadMedida unidadMedidaAnt = unidadMedidaRepository.findById(idUnidadMedida).get();
        if (unidadMedidaAnt != null) {
            unidadMedidaAct.setIdUnidadMedida(unidadMedidaAnt.getIdUnidadMedida());
            unidadMedidaRepository.save(unidadMedidaAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idUnidadMedida}")
    public ResponseEntity<Void> delete(@PathVariable Long idUnidadMedida) {
        if (unidadMedidaRepository.findById(idUnidadMedida).get() != null) {
            unidadMedidaRepository.deleteById(idUnidadMedida);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


