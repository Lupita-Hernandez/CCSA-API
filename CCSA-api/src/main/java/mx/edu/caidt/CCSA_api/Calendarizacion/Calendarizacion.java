package mx.edu.caidt.CCSA_api.Calendarizacion;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Actividades.Actividad;
import mx.edu.caidt.CCSA_api.CantidadAnual.CantidadAnual;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "calendarizacion")
public class Calendarizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalendarizacion;
    @Column(nullable = false, length = 10)
    private String mes;
    @Column(nullable = false, length = 8)
    private byte cantidad;
    @Column(nullable = false, length = 8)
    private float presupuesto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCantidadAnual")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CantidadAnual cantidadAnual;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "idPresupuesto")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Presupuesto presupuesto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idActividad")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Actividad actividad;






}
