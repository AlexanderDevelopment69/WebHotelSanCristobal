package com.hotelsancristobal.repository;

import com.hotelsancristobal.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Puedes agregar métodos adicionales según sea necesario
}
