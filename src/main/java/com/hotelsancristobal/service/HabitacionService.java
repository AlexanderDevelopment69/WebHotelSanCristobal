package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Habitacion;
import com.hotelsancristobal.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;

    public Habitacion registrarHabitacion(Habitacion habitacion) {
        // Lógica para validar y guardar la habitación
        return habitacionRepository.save(habitacion);
    }

    public List<Habitacion> obtenerTodasHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> obtenerHabitacionPorId(Long id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion actualizarHabitacion(Long id, Habitacion nuevaHabitacion) {
        if (habitacionRepository.existsById(id)) {
            nuevaHabitacion.setIdHabitacion(id);
            return habitacionRepository.save(nuevaHabitacion);
        } else {
            // Manejar caso en el que la habitación no existe
            return null;
        }
    }

    public void eliminarHabitacion(Long id) {
        habitacionRepository.deleteById(id);
    }
}
