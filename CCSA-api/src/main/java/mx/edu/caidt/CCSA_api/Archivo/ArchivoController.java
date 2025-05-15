package mx.edu.caidt.CCSA_api.Archivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://ccsa-a.vercel.app"
})

@RestController
@RequestMapping("/archivo")
public class ArchivoController {

    @Autowired
    ArchivoRepository archivoRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Archivo>> findAll() {
        return ResponseEntity.ok(archivoRepository.findAll());
    }

    @GetMapping("/{idArchivo}")
    public ResponseEntity<Archivo> findById(@PathVariable Long idArchivo) {
        Optional<Archivo> archivoOptional = archivoRepository.findById(idArchivo);
        if (archivoOptional.isPresent()) {
            return ResponseEntity.ok(archivoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Archivo newArchivo, UriComponentsBuilder ucb) {
        Archivo saveArchivo = archivoRepository.save(newArchivo);
        URI uri = ucb
                .path("archivo/{idArchivo}")
                .buildAndExpand(saveArchivo.getIdEvidencia())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idArchivo}")
    public ResponseEntity<Void> update(@PathVariable Long idArchivo, @RequestBody Archivo archivoAct) {
        Archivo archivoAnt = archivoRepository.findById(idArchivo).get();
        if (archivoAnt != null) {
            archivoAct.setIdEvidencia(archivoAnt.getIdEvidencia());
            archivoRepository.save(archivoAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idArchivo}")
    public ResponseEntity<Void> delete(@PathVariable Long idArchivo) {
        if (archivoRepository.findById(idArchivo).get() != null) {
            archivoRepository.deleteById(idArchivo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
