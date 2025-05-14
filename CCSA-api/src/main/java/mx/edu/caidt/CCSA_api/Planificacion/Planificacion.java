package mx.edu.caidt.CCSA_api.Planificacion;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Actividades.Actividad;
import mx.edu.caidt.CCSA_api.Archivo.Archivo;
import mx.edu.caidt.CCSA_api.Autorizacion.Autorizacion;
import mx.edu.caidt.CCSA_api.Componente.Componente;
import mx.edu.caidt.CCSA_api.ObjetivoEstrategico.ObjetivoEstrategico;
import mx.edu.caidt.CCSA_api.UnidadResponsable.UnidadResponsable;
import mx.edu.caidt.CCSA_api.Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Planificacion")
public class Planificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlanificacion;
    @Column(nullable = false, length = 55)
    private String jefeUnidad;
    @Column(nullable = false, length = 90)
    private String objetivoArea;
    @Column(nullable = false, length = 5)
    private byte numeroActividades;

    @OneToOne(mappedBy = "planificacion", cascade = CascadeType.ALL)
    private Usuario usuario;
//    @OneToMany(mappedBy = "planificacion")
//    private List<MedioVerificacion> mediosVerificacion;

    @OneToOne
    @JoinColumn(name = "idAutorizacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Autorizacion autorizacion;

    @OneToMany(mappedBy = "planificacion")
    private List<Actividad> actividad;

    @OneToOne(mappedBy = "planificacion", cascade = CascadeType.ALL)
    private Archivo archivo;
}

