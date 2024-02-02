package com.hotelsancristobal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Data
@Table(name = "tipos_habitaciones_eventos")
public class TipoHabitacionEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_habitacion_evento")
    private Long idTipoHabitacionEvento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_habitacion", nullable = false)
    private TipoHabitacion tipoHabitacion;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @Column(name = "costo_temporada", nullable = false)
    private BigDecimal costoTemporada;
}