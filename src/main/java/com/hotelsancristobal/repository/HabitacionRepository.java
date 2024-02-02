package com.hotelsancristobal.repository;

import com.hotelsancristobal.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    // Puedes agregar métodos adicionales según sea necesario

    List<Habitacion> findByTipoHabitacionIdTipoHabitacion(Long tipoId);
}
