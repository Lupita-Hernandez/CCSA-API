package mx.edu.caidt.CCSA_api.ObjetivoEstrategico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/objetivoEstrategico")
public class ObjetivoEstrategicoController {

    @Autowired
    ObjetivoEstrategicoRepository objetivoEstrategicoRepository;

    @GetMapping()
    public ResponseEntity<Iterable<ObjetivoEstrategico>> findAll() {
        return ResponseEntity.ok(objetivoEstrategicoRepository.findAll());
    }

    @GetMapping("/{idObjetivoEstrategico}")
    public ResponseEntity<ObjetivoEstrategico> findById(@PathVariable Long idObjetivoEstrategico) {
        Optional<ObjetivoEstrategico> objetivoEstrategicoOptional = objetivoEstrategicoRepository.findById(idObjetivoEstrategico);
        if (objetivoEstrategicoOptional.isPresent()) {
            return ResponseEntity.ok(objetivoEstrategicoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ObjetivoEstrategico newObjetivoEstrategico, UriComponentsBuilder ucb) {
        ObjetivoEstrategico saveObjetivoEstrategico = objetivoEstrategicoRepository.save(newObjetivoEstrategico);
        URI uri = ucb
                .path("objetivoEstrategico/{idObjetivoEstrategico}")
                .buildAndExpand(saveObjetivoEstrategico.getIdObjetivoEstrategico())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idObjetivoEstrategico}")
    public ResponseEntity<Void> update(@PathVariable Long idObjetivoEstrategico, @RequestBody ObjetivoEstrategico objetivoEstrategicoAct) {
        ObjetivoEstrategico objetivoEstrategicoAnt = objetivoEstrategicoRepository.findById(idObjetivoEstrategico).get();
        if (objetivoEstrategicoAnt != null) {
            objetivoEstrategicoAct.setIdObjetivoEstrategico(objetivoEstrategicoAnt.getIdObjetivoEstrategico());
            objetivoEstrategicoRepository.save(objetivoEstrategicoAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idObjetivoEstrategico}")
    public ResponseEntity<Void> delete(@PathVariable Long idObjetivoEstrategico) {
        if (objetivoEstrategicoRepository.findById(idObjetivoEstrategico).get() != null) {
            objetivoEstrategicoRepository.deleteById(idObjetivoEstrategico);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


