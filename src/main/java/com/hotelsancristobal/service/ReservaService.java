package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Reserva;
import com.hotelsancristobal.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva registrarReserva(Reserva reserva) {
        // Lógica para validar y guardar la reserva
        return reservaRepository.save(reserva);
    }

    public List<Reserva> obtenerTodasReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva actualizarReserva(Long id, Reserva nuevaReserva) {
        if (reservaRepository.existsById(id)) {
            nuevaReserva.setIdReserva(id);
            return reservaRepository.save(nuevaReserva);
        } else {
            // Manejar caso en el que la reserva no existe
            return null;
        }
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
