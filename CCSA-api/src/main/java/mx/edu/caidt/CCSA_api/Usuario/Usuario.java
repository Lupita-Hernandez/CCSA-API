package mx.edu.caidt.CCSA_api.Usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Actividades.Actividad;
import mx.edu.caidt.CCSA_api.Planificacion.Planificacion;
import mx.edu.caidt.CCSA_api.Rol.Rol;
import mx.edu.caidt.CCSA_api.UnidadResponsable.UnidadResponsable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column(nullable = false, length = 40)
    private String nombre;
    @Column(nullable = false, length = 50, unique = true)
    private String correo;
    @Column(nullable = false, length = 20)
    private String contraseña;

//    @OneToMany(mappedBy = "usuario")
//    private List<UsuarioRol> usuarioRol;
//    @OneToMany(mappedBy = "usuario")
//    @JsonProperty("rol")
//    private List<Rol> Rol;

    @OneToOne
    @JoinColumn(name = "idPlanificacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Planificacion planificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUnidadResponsable")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UnidadResponsable unidadResponsable;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idRol")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Rol rol;
}
