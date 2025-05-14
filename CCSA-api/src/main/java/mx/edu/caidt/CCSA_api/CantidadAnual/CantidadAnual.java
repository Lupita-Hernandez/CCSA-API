package mx.edu.caidt.CCSA_api.CantidadAnual;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.edu.caidt.CCSA_api.Calendarizacion.Calendarizacion;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "cantidadAnual")
public class CantidadAnual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCantidadAnual;
    @Column(nullable = false, length = 3)
    private byte cantidad;

    @OneToMany(mappedBy = "cantidadAnual")
    private List<Calendarizacion> calendarizaciones;
}
