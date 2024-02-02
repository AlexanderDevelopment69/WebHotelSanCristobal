package com.hotelsancristobal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long idHabitacion;

    @Column(name = "numero_habitacion", nullable = false)
    private int numeroHabitacion;

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado = EstadoHabitacion.DISPONIBLE;

    @ManyToOne
    @JoinColumn(name = "id_tipo_habitacion")
    private TipoHabitacion tipoHabitacion;

    // Otros métodos o lógica de negocio si es necesario
}