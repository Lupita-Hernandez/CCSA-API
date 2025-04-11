package mx.edu.caidt.CCSA_api.Autorizacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/autorizacion")
public class AutorizacionController {

    @Autowired
    AutorizacionRepository autorizacionRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Autorizacion>> findAll() {
        return ResponseEntity.ok(autorizacionRepository.findAll());
    }

    @GetMapping("/{idAutorizacion}")
    public ResponseEntity<Autorizacion> findById(@PathVariable Long idAutorizacion) {
        Optional<Autorizacion> autorizacionOptional = autorizacionRepository.findById(idAutorizacion);
        if (autorizacionOptional.isPresent()) {
            return ResponseEntity.ok(autorizacionOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Autorizacion newAutorizacion, UriComponentsBuilder ucb) {
        Autorizacion saveAutorizacion = autorizacionRepository.save(newAutorizacion);
        URI uri = ucb
                .path("autorizacion/{idAutorizacion}")
                .buildAndExpand(saveAutorizacion.getIdAutorizacion())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idAutorizacion}")
    public ResponseEntity<Void> update(@PathVariable Long idAutorizacion, @RequestBody Autorizacion autorizacionAct) {
        Autorizacion autorizacionAnt = autorizacionRepository.findById(idAutorizacion).get();
        if (autorizacionAnt != null) {
            autorizacionAct.setIdAutorizacion(autorizacionAnt.getIdAutorizacion());
            autorizacionRepository.save(autorizacionAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idAutorizacion}")
    public ResponseEntity<Void> delete(@PathVariable Long idAutorizacion) {
        if (autorizacionRepository.findById(idAutorizacion).get() != null) {
            autorizacionRepository.deleteById(idAutorizacion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}