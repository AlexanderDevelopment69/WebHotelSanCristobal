package com.hotelsancristobal.repository;

// ChatbotInteraccionRepository.java
import com.hotelsancristobal.model.ChatbotInteraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotInteraccionRepository extends JpaRepository<ChatbotInteraccion, Long> {
    // Puedes agregar métodos adicionales según sea necesario
}
