package com.hotelsancristobal.service;

// ChatbotInteraccionService.java
import com.hotelsancristobal.model.ChatbotInteraccion;
import com.hotelsancristobal.repository.ChatbotInteraccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatbotInteraccionService {
    @Autowired
    private ChatbotInteraccionRepository chatbotInteraccionRepository;

    public ChatbotInteraccion guardarInteraccion(ChatbotInteraccion interaccion) {
        return chatbotInteraccionRepository.save(interaccion);
    }

    public List<ChatbotInteraccion> obtenerTodasInteracciones() {
        return chatbotInteraccionRepository.findAll();
    }

    // Puedes agregar más métodos según sea necesario
}
