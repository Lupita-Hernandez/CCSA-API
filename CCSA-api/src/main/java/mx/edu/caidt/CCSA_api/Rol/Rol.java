package mx.edu.caidt.CCSA_api.Rol;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Usuario.Usuario;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    @Column(nullable = false, length = 40)
    private String nombreRol;


//    @OneToMany(mappedBy = "rol")
//    private List<UsuarioRol> usuarioRol;

//    @ManyToOne
//    @JoinColumn(name = "idUsuario")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Usuario usuario;

    @OneToMany(mappedBy = "rol")
   // @JsonProperty("usuario")
    private List<Usuario> Usuario;
}

