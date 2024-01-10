package com.hotelsancristobal.model;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Data
@Entity
@Table(name = "chatbot_interacciones")
public class ChatbotInteraccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interaccion")
    private Long idInteraccion;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "tipo_interaccion", nullable = false)
    private String tipoInteraccion;

    @Column(name = "mensaje_usuario", nullable = false)
    private String mensajeUsuario;

    @Column(name = "respuesta_chatbot", nullable = false)
    private String respuestaChatbot;

    @Column(name = "fecha_interaccion")
    private Date fechaInteraccion;




}
