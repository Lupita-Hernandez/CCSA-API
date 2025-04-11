package mx.edu.caidt.CCSA_api.Componente;
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
@Table(name = "Componente")
public class Componente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComponente;
    @Column(nullable = false, length = 7)
    private byte componente;
    @Column(nullable = false, length = 12)
    private String nivel;
    @Column(nullable = false, length = 130)
    private String nombreIndicador;

    @OneToMany(mappedBy = "componente")
    private List<Actividad> actividad;

}
