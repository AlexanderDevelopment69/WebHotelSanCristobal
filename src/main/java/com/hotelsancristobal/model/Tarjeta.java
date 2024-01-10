package com.hotelsancristobal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tarjetas")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta")
    private Long idTarjeta;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "token_tarjeta")
    private String tokenTarjeta;
}