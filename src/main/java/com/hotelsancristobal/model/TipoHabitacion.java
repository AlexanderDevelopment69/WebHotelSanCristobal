package com.hotelsancristobal.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_habitacion")
    private Long idTipoHabitacion;

    @Column(name = "nombre_tipo", nullable = false)
    private String nombreTipo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "servicios")
    private String servicios;
}
