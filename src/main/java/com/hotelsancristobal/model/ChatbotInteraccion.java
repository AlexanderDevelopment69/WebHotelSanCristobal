package com.hotelsancristobal.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Entity
@Table(name = "chatbot_interacciones")
public class ChatbotInteraccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interaccion")
    private Long idInteraccion;

    @Column(name = "nombres_usuario")
    private String nombresUsuario;

    @Column(name = "correo_usuario")
    private String correoUsuario;

    @Column(name = "tipo_interaccion", nullable = false)
    private String tipoInteraccion;

//    @Column(name = "mensaje_usuario", nullable = false)
//    private String mensajeUsuario;
//
//    @Column(name = "respuesta_chatbot", nullable = false)
//    private String respuestaChatbot;


    @Lob
    @Column(name = "mensaje_usuario", nullable = false, columnDefinition = "TEXT")
    private String mensajeUsuario;

    @Lob
    @Column(name = "respuesta_chatbot", nullable = false, columnDefinition = "TEXT")
    private String respuestaChatbot;


    @Column(name = "fecha_interaccion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaInteraccion;

//    @Temporal(TemporalType.TIMESTAMP)
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    @Column(name = "fecha_interaccion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private Date fechaInteraccion;

}
