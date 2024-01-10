package com.hotelsancristobal.model;


import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_tarjeta")
    private Tarjeta tarjeta;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "fecha_pago")
    private Date fechaPago;
}