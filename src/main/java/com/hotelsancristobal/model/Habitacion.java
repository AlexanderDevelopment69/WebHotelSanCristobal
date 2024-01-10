package com.hotelsancristobal.model;

import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long idHabitacion;

    @Column(name = "capacidad")
    private int capacidad;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "disponible")
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_tipo_habitacion", nullable = false)
    private TipoHabitacion tipoHabitacion;
}