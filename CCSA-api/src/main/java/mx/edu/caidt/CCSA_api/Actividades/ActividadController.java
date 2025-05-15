package mx.edu.caidt.CCSA_api.Actividades;

import mx.edu.caidt.CCSA_api.Planificacion.Planificacion;
import mx.edu.caidt.CCSA_api.Planificacion.PlanificacionRepository;
import mx.edu.caidt.CCSA_api.UnidadMedida.UnidadMedida;
import mx.edu.caidt.CCSA_api.UnidadMedida.UnidadMedidaRepository;
import mx.edu.caidt.CCSA_api.ObjetivoEstrategico.ObjetivoEstrategico;
import mx.edu.caidt.CCSA_api.ObjetivoEstrategico.ObjetivoEstrategicoRepository;
import mx.edu.caidt.CCSA_api.Componente.Componente;
import mx.edu.caidt.CCSA_api.Componente.ComponenteRepository;
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
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private ActividadRepository actividadRepository;
    @Autowired
    private PlanificacionRepository planificacionRepository;
    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;
    @Autowired
    private ObjetivoEstrategicoRepository objetivoEstrategicoRepository;
    @Autowired
    private ComponenteRepository componenteRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Actividad>> findAll() {
        return ResponseEntity.ok(actividadRepository.findAll());
    }

    @GetMapping("/{idActividad}")
    public ResponseEntity<Actividad> findById(@PathVariable Long idActividad) {
        Optional<Actividad> actividadOptional = actividadRepository.findById(idActividad);
        return actividadOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Actividad> create(@RequestBody Actividad actividad, UriComponentsBuilder ucBuilder) {

        Optional<Planificacion> planificacionOptional = planificacionRepository.findById(actividad.getPlanificacion().getIdPlanificacion());
        Optional<UnidadMedida> unidadMedidaOptional = unidadMedidaRepository.findById(actividad.getUnidadMedida().getIdUnidadMedida());
        Optional<ObjetivoEstrategico> objetivoEstrategicoOptional = objetivoEstrategicoRepository.findById(actividad.getObjetivoEstrategico().getIdObjetivoEstrategico());
        Optional<Componente> componenteOptional = componenteRepository.findById(actividad.getComponente().getIdComponente());

        if (!planificacionOptional.isPresent() || !unidadMedidaOptional.isPresent() || !objetivoEstrategicoOptional.isPresent() || !componenteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        actividad.setPlanificacion(planificacionOptional.get());
        actividad.setUnidadMedida(unidadMedidaOptional.get());
        actividad.setObjetivoEstrategico(objetivoEstrategicoOptional.get());
        actividad.setComponente(componenteOptional.get());

        Actividad created = actividadRepository.save(actividad);
        URI uri = ucBuilder.path("/actividad/{idActividad}").buildAndExpand(created.getIdActividad()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idActividad}")
    public ResponseEntity<Void> update(@PathVariable Long idActividad, @RequestBody Actividad actividad) {

        Optional<Planificacion> planificacionOptional = planificacionRepository.findById(actividad.getPlanificacion().getIdPlanificacion());
        Optional<UnidadMedida> unidadMedidaOptional = unidadMedidaRepository.findById(actividad.getUnidadMedida().getIdUnidadMedida());
        Optional<ObjetivoEstrategico> objetivoEstrategicoOptional = objetivoEstrategicoRepository.findById(actividad.getObjetivoEstrategico().getIdObjetivoEstrategico());
        Optional<Componente> componenteOptional = componenteRepository.findById(actividad.getComponente().getIdComponente());

        if (!planificacionOptional.isPresent() || !unidadMedidaOptional.isPresent() || !objetivoEstrategicoOptional.isPresent() || !componenteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Actividad> actividadAnterior = actividadRepository.findById(idActividad);
        if (actividadAnterior.isPresent()) {
            actividad.setPlanificacion(planificacionOptional.get());
            actividad.setUnidadMedida(unidadMedidaOptional.get());
            actividad.setObjetivoEstrategico(objetivoEstrategicoOptional.get());
            actividad.setComponente(componenteOptional.get());
            actividad.setIdActividad(actividadAnterior.get().getIdActividad());
            actividadRepository.save(actividad);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


