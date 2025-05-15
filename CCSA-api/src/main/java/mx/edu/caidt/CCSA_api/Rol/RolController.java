package mx.edu.caidt.CCSA_api.Rol;

import mx.edu.caidt.CCSA_api.Usuario.Usuario;
import mx.edu.caidt.CCSA_api.Usuario.UsuarioRepository;
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
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolRepository rolRepository;

    @GetMapping
    public ResponseEntity<Iterable<Rol>> findAll() {
        return ResponseEntity.ok(rolRepository.findAll());
    }

    @GetMapping("/{idRol}")
    public ResponseEntity<Rol> findById(@PathVariable Long idRol) {
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        if(rolOptional.isPresent()) {
            return ResponseEntity.ok(rolOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Rol newRol, UriComponentsBuilder ucb) {
        Rol savedRol = rolRepository.save(newRol);
        URI uri = ucb.path("/rol/{idRol}")
                .buildAndExpand(savedRol.getIdRol())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idRol}")
    public ResponseEntity<Void> update(@PathVariable Long idRol, @RequestBody Rol rolUpdated) {
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        if (rolOptional.isPresent()) {
            rolUpdated.setIdRol(idRol);
            rolRepository.save(rolUpdated);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<Void> delete(@PathVariable Long idRol) {
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        if (rolOptional.isPresent()) {
            rolRepository.deleteById(idRol);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

