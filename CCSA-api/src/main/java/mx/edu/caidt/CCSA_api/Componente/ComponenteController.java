package mx.edu.caidt.CCSA_api.Componente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/componente")
public class ComponenteController {

    @Autowired
    ComponenteRepository componenteRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Componente>> findAll() {
        return ResponseEntity.ok(componenteRepository.findAll());
    }

    @GetMapping("/{idComponente}")
    public ResponseEntity<Componente> findById(@PathVariable Long idComponente) {
        Optional<Componente> componenteOptional = componenteRepository.findById(idComponente);
        if (componenteOptional.isPresent()) {
            return ResponseEntity.ok(componenteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Componente newComponente, UriComponentsBuilder ucb) {
        Componente saveComponente = componenteRepository.save(newComponente);
        URI uri = ucb
                .path("componente/{idComponente}")
                .buildAndExpand(saveComponente.getIdComponente())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idComponente}")
    public ResponseEntity<Void> update(@PathVariable Long idComponente, @RequestBody Componente componenteAct) {
        Componente componenteAnt = componenteRepository.findById(idComponente).get();
        if (componenteAnt != null) {
            componenteAct.setIdComponente(componenteAnt.getIdComponente());
            componenteRepository.save(componenteAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idComponente}")
    public ResponseEntity<Void> delete(@PathVariable Long idComponente) {
        if (componenteRepository.findById(idComponente).get() != null) {
            componenteRepository.deleteById(idComponente);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

