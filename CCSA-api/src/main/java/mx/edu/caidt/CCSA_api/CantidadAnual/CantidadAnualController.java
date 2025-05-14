package mx.edu.caidt.CCSA_api.CantidadAnual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/cantidadAnual")
public class CantidadAnualController {

    @Autowired
    CantidadAnualRepository cantidadAnualRepository;

    @GetMapping()
    public ResponseEntity<Iterable<CantidadAnual>> findAll() {
        return ResponseEntity.ok(cantidadAnualRepository.findAll());
    }

    @GetMapping("/{idCantidadAnual}")
    public ResponseEntity<CantidadAnual> findById(@PathVariable Long idCantidadAnual) {
        Optional<CantidadAnual> cantidadAnualOptional = cantidadAnualRepository.findById(idCantidadAnual);
        if (cantidadAnualOptional.isPresent()) {
            return ResponseEntity.ok(cantidadAnualOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CantidadAnual newCantidadAnual, UriComponentsBuilder ucb) {
        CantidadAnual saveCantidadAnual = cantidadAnualRepository.save(newCantidadAnual);
        URI uri = ucb
                .path("cantidadAnual/{idCantidadAnual}")
                .buildAndExpand(saveCantidadAnual.getIdCantidadAnual())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idCantidadAnual}")
    public ResponseEntity<Void> update(@PathVariable Long idCantidadAnual, @RequestBody CantidadAnual cantidadAnualAct) {
        CantidadAnual cantidadAnualAnt = cantidadAnualRepository.findById(idCantidadAnual).get();
        if (cantidadAnualAnt != null) {
            cantidadAnualAct.setIdCantidadAnual(cantidadAnualAnt.getIdCantidadAnual());
            cantidadAnualRepository.save(cantidadAnualAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idCantidadAnual}")
    public ResponseEntity<Void> delete(@PathVariable Long idCantidadAnual) {
        if (cantidadAnualRepository.findById(idCantidadAnual).get() != null) {
            cantidadAnualRepository.deleteById(idCantidadAnual);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
