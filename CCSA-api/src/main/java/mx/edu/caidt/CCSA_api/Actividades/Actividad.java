package mx.edu.caidt.CCSA_api.Actividades;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Calendarizacion.Calendarizacion;
import mx.edu.caidt.CCSA_api.Componente.Componente;
import mx.edu.caidt.CCSA_api.ObjetivoEstrategico.ObjetivoEstrategico;
import mx.edu.caidt.CCSA_api.Planificacion.Planificacion;
import mx.edu.caidt.CCSA_api.UnidadMedida.UnidadMedida;
import mx.edu.caidt.CCSA_api.UnidadResponsable.UnidadResponsable;
import mx.edu.caidt.CCSA_api.Usuario.Usuario;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;
    @Column(nullable = false, length = 150)
    private String descripcion;
    @Column(nullable = false, length = 80)
    private String medioVerificacion;
    @Column(nullable = false, length = 100)
    private String indicadorResultados;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPlanificacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Planificacion planificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUnidadMedida")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UnidadMedida unidadMedida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idObjetivoEstrategico")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ObjetivoEstrategico objetivoEstrategico;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idComponente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Componente componente;

//    @OneToMany(mappedBy = "actividad")
//    private List<ObjetivoEstrategico> objetivoEstrategico;
//
//    @OneToMany(mappedBy = "actividad")
//    private List<Componente> componente;
//
//    @OneToMany(mappedBy = "actividad")
//    private List<UnidadMedida> unidadMedida;

    @OneToMany(mappedBy = "actividad")
    private List<Calendarizacion> calendarizacion;





}

