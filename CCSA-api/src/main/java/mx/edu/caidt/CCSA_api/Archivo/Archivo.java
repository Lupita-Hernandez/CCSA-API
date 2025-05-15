package mx.edu.caidt.CCSA_api.Archivo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Planificacion.Planificacion;
import mx.edu.caidt.CCSA_api.Usuario.Usuario;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Evidencia")
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvidencia;
    @Column(nullable = false, length = 30)
    private String nombre;

    private Date fecha;
    @Column(nullable = false, length = 35)
    private String Trimestre;
    @Column(nullable = false, length = 3)
    private byte NoActividad;
    @Column(nullable = false, length = 200)
    private String foto1;
    @Column(nullable = false, length = 30)
    private String descripcionfoto1;

    private Date fechafoto1;
    @Column(nullable = false, length = 200)
    private String foto2;
    @Column(nullable = false, length = 30)
    private String descripcionfoto2;

    private Date fechafoto2;
    @Column(nullable = false, length = 200)
    private String foto3;
    @Column(nullable = false, length = 30)
    private String descripcionfoto3;
    @Column(nullable = false, length = 30)
    private Date fechafoto3;
    @Column(nullable = false, length = 200)
    private String firmaResponsable;

    @OneToOne
    @JoinColumn(name = "idPlanificacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Planificacion planificacion;

}
