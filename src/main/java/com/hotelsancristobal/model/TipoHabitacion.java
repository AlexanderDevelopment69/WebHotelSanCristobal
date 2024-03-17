package com.hotelsancristobal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@Table(name = "tipos_habitaciones")
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_habitacion")
    private Long idTipoHabitacion;

    @Column(name = "nombre_tipo", nullable = false)
    private String nombreTipo;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "cantidad_total", nullable = false)
    private int cantidadTotal;

    @Column(name = "cantidad_disponible", nullable = false)
    private int cantidadDisponible;

    @OneToMany(mappedBy = "tipoHabitacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoHabitacionEvento> tiposHabitacionEventos;

//    @OneToMany(mappedBy = "tipoHabitacion", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Servicio> servicios;


    @OneToMany(mappedBy = "tipoHabitacion", fetch = FetchType.LAZY)
    private List<Servicio> servicios;


    @Override
    public String toString() {
        return "TipoHabitacion{" +
                "idTipoHabitacion=" + idTipoHabitacion +
                ", nombreTipo='" + nombreTipo + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", cantidadTotal=" + cantidadTotal +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
