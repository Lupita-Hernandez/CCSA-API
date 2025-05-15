package mx.edu.caidt.CCSA_api.Autorizacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Planificacion.Planificacion;
import mx.edu.caidt.CCSA_api.Usuario.Usuario;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Autorizacion")
public class Autorizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutorizacion;

    @Column(nullable = false, length = 50)
    private String elaboradoPor;
    @Column(nullable = false, length = 100)
    private String elaboradoPorCargo;

    @Column(nullable = false, length = 100)
    private String voBoPorCargo;

    @Column(nullable = false, length = 100)
    private String autorizadoPorCargo;

    @Column(nullable = false, length = 100)
    private String recibidoPorCargo;

    @Column(nullable = false, length = 100)
    private String asignadoPorCargo;

    @Column(nullable = false, length = 100)
    private String ejecucionPorCargo;

    // Rutas de firmas digitales
    @Column(nullable = false, length = 200)
    private String rutaFirmaElaboradoPor;
    @Column(nullable = false, length = 200)
    private String rutaFirmaVoBoPor;
    @Column(nullable = false, length = 200)
    private String rutaFirmaAutorizadoPor;
    @Column(nullable = false, length = 200)
    private String rutaFirmaRecibidoPor;
    @Column(nullable = false, length = 200)
    private String rutaFirmaAsignadoPor;
    @Column(nullable = false, length = 200)
    private String rutaFirmaEjecucionPor;

    @OneToOne(mappedBy = "autorizacion", cascade = CascadeType.ALL)
    private Planificacion planificacion;
}

