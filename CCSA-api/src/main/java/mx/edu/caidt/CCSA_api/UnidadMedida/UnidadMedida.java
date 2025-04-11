package mx.edu.caidt.CCSA_api.UnidadMedida;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Actividades.Actividad;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "UnidadMedida")
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidadMedida;
    @Column(nullable = false, length = 3)
    private byte UnidadMedida;
    @Column(nullable = false, length = 10)
    private String tipo;

    @OneToMany(mappedBy = "unidadMedida")
    private List<Actividad> actividad;




}
