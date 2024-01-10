package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.ChatbotInteraccion;
import com.hotelsancristobal.service.ChatbotInteraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatbot/interacciones")
public class ChatbotInteraccionController {
    @Autowired
    private ChatbotInteraccionService chatbotInteraccionService;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarInteraccion(@RequestBody ChatbotInteraccion interaccion) {
        chatbotInteraccionService.guardarInteraccion(interaccion);
        return new ResponseEntity<>("Interacción guardada exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/todas")
    public List<ChatbotInteraccion> obtenerTodasInteracciones() {
        return chatbotInteraccionService.obtenerTodasInteracciones();
    }

    // Puedes agregar más métodos según sea necesario
}
