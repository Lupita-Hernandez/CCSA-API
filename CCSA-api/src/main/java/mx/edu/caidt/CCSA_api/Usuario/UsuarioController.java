package mx.edu.caidt.CCSA_api.Usuario;

import mx.edu.caidt.CCSA_api.Planificacion.Planificacion;
import mx.edu.caidt.CCSA_api.Planificacion.PlanificacionRepository;
import mx.edu.caidt.CCSA_api.Rol.Rol;
import mx.edu.caidt.CCSA_api.Rol.RolRepository;
import mx.edu.caidt.CCSA_api.UnidadResponsable.UnidadResponsable;
import mx.edu.caidt.CCSA_api.UnidadResponsable.UnidadResponsableRepository;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanificacionRepository planificacionRepository;

    @Autowired
    private UnidadResponsableRepository unidadResponsableRepository;

//    @Autowired
//    private RolRepository rolRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> findById(@PathVariable Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
        if (usuario.getPlanificacion() == null) {
            return ResponseEntity.badRequest().body("La planificación no puede ser nula.");
        }

        Optional<Planificacion> planificacionOptional = planificacionRepository.findById(usuario.getPlanificacion().getIdPlanificacion());
        if (!planificacionOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().body("La planificación con el ID especificado no existe.");
        }

        if (usuario.getUnidadResponsable() == null) {
            return ResponseEntity.badRequest().body("La unidad responsable no puede ser nula.");
        }

        Optional<UnidadResponsable> unidadResponsableOptional = unidadResponsableRepository.findById(usuario.getUnidadResponsable().getIdUnidadResponsable());
        if (!unidadResponsableOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().body("La unidad responsable con el ID especificado no existe.");
        }

//        if (usuario.getRol() == null){
//            return ResponseEntity.badRequest().body("La rol no puede ser nula.");
//        }
//
//        Optional<Rol> rolOptional = rolRepository.findById(usuario.getRol().getIdRol());
//        if (!rolOptional.isPresent()) {
//            return ResponseEntity.unprocessableEntity().body("El rol con el ID especificado no existe.");
//        }

        usuario.setPlanificacion(planificacionOptional.get());
        usuario.setUnidadResponsable(unidadResponsableOptional.get());
        //usuario.setRol(rolOptional.get());
        Usuario created = usuarioRepository.save(usuario);
        URI uri = ucBuilder.path("/usuario/{idUsuario}").buildAndExpand(created.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).body(created);
    }


    @PutMapping("/{idUsuario}")
    public ResponseEntity<Void> update(@PathVariable Long idUsuario, @RequestBody Usuario usuario) {
        if (usuario.getPlanificacion() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Planificacion> planificacionOptional = planificacionRepository.findById(usuario.getPlanificacion().getIdPlanificacion());
        if (!planificacionOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

//        if (usuario.getRol() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        Optional<Rol> rolOptional = rolRepository.findById(usuario.getRol().getIdRol());
//        if (!rolOptional.isPresent()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }

        if (usuario.getUnidadResponsable() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UnidadResponsable> unidadResponsableOptional = unidadResponsableRepository.findById(usuario.getUnidadResponsable().getIdUnidadResponsable());
        if (!unidadResponsableOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Usuario> usuarioAnterior = usuarioRepository.findById(idUsuario);
        if (!usuarioAnterior.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuario.setPlanificacion(planificacionOptional.get());
        //usuario.setRol(rolOptional.get());
        usuario.setUnidadResponsable(unidadResponsableOptional.get());
        usuario.setIdUsuario(idUsuario); // Asegurar que no se cambie el ID del usuario

        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
