package mx.edu.caidt.CCSA_api.ObjetivoEstrategico;
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
@Table(name = "objetivoEstrategico")
public class ObjetivoEstrategico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObjetivoEstrategico;
    @Column(nullable = false, length = 100)
    private String objetivo;
    @Column(nullable = false, length = 60)
    private String estrategia;
    @Column(nullable = false, length = 50)
    private String definicionEstrategia;


//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "idActividad")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Actividad actividad;
    @OneToMany(mappedBy = "objetivoEstrategico")
    private List<Actividad> actividad;

}
