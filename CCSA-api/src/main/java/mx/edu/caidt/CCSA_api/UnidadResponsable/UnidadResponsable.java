package mx.edu.caidt.CCSA_api.UnidadResponsable;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Actividades.Actividad;
import mx.edu.caidt.CCSA_api.Usuario.Usuario;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "UnidadResponsable")
public class UnidadResponsable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidadResponsable;
    @Column(nullable = false, length = 50)
    private String NombreUnidadResponsable;
    @Column(nullable = false, length = 60)
    private String JefeUnidad;

    @OneToMany(mappedBy = "unidadResponsable")
    private List<Usuario> usuarios;



//    @OneToMany(mappedBy = "unidadResponsable")
//    private List<Actividad> actividad;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "idActiviada")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Actividad actividad;

}