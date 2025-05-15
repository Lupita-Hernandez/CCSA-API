package mx.edu.caidt.CCSA_api.Planificacion;

import mx.edu.caidt.CCSA_api.Actividades.Actividad;
import mx.edu.caidt.CCSA_api.Actividades.ActividadRepository;
import mx.edu.caidt.CCSA_api.Archivo.Archivo;
import mx.edu.caidt.CCSA_api.Archivo.ArchivoRepository;
import mx.edu.caidt.CCSA_api.Autorizacion.Autorizacion;
import mx.edu.caidt.CCSA_api.Autorizacion.AutorizacionRepository;
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
@RequestMapping("/planificacion")
public class PlanificacionController {

    @Autowired
    private PlanificacionRepository planificacionRepository;
//    @Autowired
//    private ActividadRepository actividadRepository;
    @Autowired
    private AutorizacionRepository autorizacionRepository;
    @Autowired
    private ArchivoRepository archivoRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Planificacion>> findAll() {
        return ResponseEntity.ok(planificacionRepository.findAll());
    }

    @GetMapping("/{idPlanificacion}")
    public ResponseEntity<Planificacion> findById(@PathVariable Long idPlanificacion) {
        Optional<Planificacion> planificacionOptional = planificacionRepository.findById(idPlanificacion);
        return planificacionOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Planificacion> create(@RequestBody Planificacion planificacion, UriComponentsBuilder ucBuilder) {
//        Optional<Actividad> actividadOptional = actividadRepository.findById(planificacion.getActividad().getIdActividad());
//        if (!actividadOptional.isPresent()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
        Optional<Autorizacion> autorizacionOptional = autorizacionRepository.findById(planificacion.getAutorizacion().getIdAutorizacion());
        if (!autorizacionOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Archivo> archivoOptional = archivoRepository.findById(planificacion.getArchivo().getIdEvidencia());
        if (!archivoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        //planificacion.setActividad(actividadOptional.get());
        planificacion.setAutorizacion(autorizacionOptional.get());
        planificacion.setArchivo(archivoOptional.get());

        Planificacion created = planificacionRepository.save(planificacion);
        URI uri = ucBuilder.path("/planificacion/{idPlanificacion}").buildAndExpand(created.getIdPlanificacion()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idPlanificacion}")
    public ResponseEntity<Void> update(@PathVariable Long idPlanificacion, @RequestBody Planificacion planificacion) {
//        Optional<Actividad> actividadOptional = actividadRepository.findById(planificacion.getActividad().getIdActividad());
//        if (!actividadOptional.isPresent()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
        Optional<Autorizacion> autorizacionOptional = autorizacionRepository.findById(planificacion.getAutorizacion().getIdAutorizacion());
        if (!autorizacionOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Archivo> archivoOptional = archivoRepository.findById(planificacion.getArchivo().getIdEvidencia());
        if (!archivoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Planificacion> planificacionAnteriorOptional = planificacionRepository.findById(idPlanificacion);
        if (planificacionAnteriorOptional.isPresent()) {
           // planificacion.setActividad(actividadOptional.get());
            planificacion.setAutorizacion(autorizacionOptional.get());
            planificacion.setArchivo(archivoOptional.get());
            planificacion.setIdPlanificacion(planificacionAnteriorOptional.get().getIdPlanificacion());
            planificacionRepository.save(planificacion);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

