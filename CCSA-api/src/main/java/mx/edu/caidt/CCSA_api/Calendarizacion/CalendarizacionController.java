package mx.edu.caidt.CCSA_api.Calendarizacion;

import mx.edu.caidt.CCSA_api.Actividades.Actividad;
import mx.edu.caidt.CCSA_api.Actividades.ActividadRepository;
import mx.edu.caidt.CCSA_api.CantidadAnual.CantidadAnual;
import mx.edu.caidt.CCSA_api.CantidadAnual.CantidadAnualRepository;
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
@RequestMapping("/calendarizacion")
public class CalendarizacionController {
    @Autowired
    private CalendarizacionRepository calendarizacionRepository;
    @Autowired
    private CantidadAnualRepository cantidadAnualRepository;
    @Autowired
    private ActividadRepository actividadRepository;


    @GetMapping()
    public ResponseEntity<Iterable<Calendarizacion>> findAll() {
        return ResponseEntity.ok(calendarizacionRepository.findAll());
    }

    @GetMapping("/{idCalendarizacion}")
    public ResponseEntity<Calendarizacion> findById(@PathVariable Long idCalendarizacion) {
        Optional<Calendarizacion> calendarizacionOptional = calendarizacionRepository.findById(idCalendarizacion);
        if (calendarizacionOptional.isPresent()) {
            return ResponseEntity.ok(calendarizacionOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Calendarizacion> create(@RequestBody Calendarizacion calendarizacion, UriComponentsBuilder ucBuilder) {
        Optional<CantidadAnual> cantidadAnualOptional = cantidadAnualRepository.findById(calendarizacion.getCantidadAnual().getIdCantidadAnual());
        if (!cantidadAnualOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Actividad> actividadOptional = actividadRepository.findById(calendarizacion.getActividad().getIdActividad());
        if (!actividadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        calendarizacion.setCantidadAnual(cantidadAnualOptional.get());
        calendarizacion.setActividad(actividadOptional.get());
        Calendarizacion created = calendarizacionRepository.save(calendarizacion);
        URI uri = ucBuilder.path("/calendarizacion/{idCalendarizacion}").buildAndExpand(created.getIdCalendarizacion()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idCalendarizacion}")
    public ResponseEntity<Void> update(@PathVariable Long idCalendarizacion, @RequestBody Calendarizacion calendarizacion) {
        Optional<CantidadAnual> cantidadAnualOptional = cantidadAnualRepository.findById(calendarizacion.getCantidadAnual().getIdCantidadAnual());
        if (!cantidadAnualOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Actividad> actividadOptional = actividadRepository.findById(calendarizacion.getActividad().getIdActividad());
        if (!actividadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Calendarizacion calendarizacionAnterior = calendarizacionRepository.findById(idCalendarizacion).get();
        if (calendarizacionAnterior!=null){
            calendarizacion.setCantidadAnual(cantidadAnualOptional.get());
            calendarizacion.setActividad(actividadOptional.get());

            calendarizacion.setIdCalendarizacion(calendarizacionAnterior.getIdCalendarizacion());
            calendarizacionRepository.save(calendarizacion);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
