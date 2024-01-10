package com.hotelsancristobal.repository;

import com.hotelsancristobal.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Puedes agregar métodos adicionales según sea necesario
}
