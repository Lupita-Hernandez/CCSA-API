package mx.edu.caidt.CCSA_api.Rol;

import mx.edu.caidt.CCSA_api.Usuario.Usuario;
import mx.edu.caidt.CCSA_api.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Rol>> findAll() {
        return ResponseEntity.ok(rolRepository.findAll());
    }

    @GetMapping("/{idRol}")
    public ResponseEntity<Rol> findById(@PathVariable Long idRol) {
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        return rolOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody Rol rol, UriComponentsBuilder ucBuilder) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(rol.getUsuario().getIdUsuario());
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        rol.setUsuario(usuarioOptional.get());
        Rol created = rolRepository.save(rol);
        URI uri = ucBuilder.path("/rol/{idRol}").buildAndExpand(created.getIdRol()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idRol}")
    public ResponseEntity<Void> update(@PathVariable Long idRol, @RequestBody Rol rol) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(rol.getUsuario().getIdUsuario());
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Rol> rolAnterior = rolRepository.findById(idRol);
        if (rolAnterior.isPresent()) {
            rol.setUsuario(usuarioOptional.get());
            rol.setIdRol(rolAnterior.get().getIdRol());
            rolRepository.save(rol);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

