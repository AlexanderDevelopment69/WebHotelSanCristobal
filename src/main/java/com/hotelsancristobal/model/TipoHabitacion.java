package com.hotelsancristobal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@Table(name = "tipos_habitaciones")
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_habitacion")
    private Long idTipoHabitacion;

    @Column(name = "nombre_tipo", nullable = false)
    private String nombreTipo;

    @Column(name = "costo", nullable = false)
    private BigDecimal costo;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "tipoHabitacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servicio> servicios;

    @Column(name = "imagen")
    private String imagen;


    @Column(name = "cantidad_total", nullable = false)
    private int cantidadTotal;

    @Column(name = "cantidad_disponible", nullable = false)
    private int cantidadDisponible;

    @OneToMany(mappedBy = "tipoHabitacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoHabitacionEvento> tiposHabitacionEventos;

}
