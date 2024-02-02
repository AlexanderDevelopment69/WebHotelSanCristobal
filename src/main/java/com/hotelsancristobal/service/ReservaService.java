package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Cliente;
import com.hotelsancristobal.model.EstadoReserva;
import com.hotelsancristobal.model.Habitacion;
import com.hotelsancristobal.model.Reserva;
import com.hotelsancristobal.repository.ClienteRepository;
import com.hotelsancristobal.repository.HabitacionRepository;
import com.hotelsancristobal.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

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


    public boolean verificarDisponibilidad(Long idHabitacion, String fechaInicio, String fechaFin) {
        // Lógica para verificar disponibilidad en la base de datos
        // Implementa el método que mencioné anteriormente para verificar la disponibilidad.
        // Retorna true si la habitación está disponible, false en caso contrario.
        return true; // Placeholder, reemplaza con la lógica real.
    }

//    public Reserva realizarReserva(Long idHabitacion, String fechaInicio, String fechaFin, String nombreCliente, String correoCliente) {
//        Habitacion habitacion = habitacionRepository.findById(idHabitacion).orElseThrow(() -> new ChangeSetPersister.NotFoundException("Habitación no encontrada"));
//
//        // Crea una nueva instancia de Reserva
//        Reserva reserva = new Reserva();
//        reserva.setHabitacion(habitacion);
//        reserva.setFechaInicio(fechaInicio);
//        reserva.setFechaFin(fechaFin);
//        reserva.setNombreCliente(nombreCliente);
//        reserva.setCorreoCliente(correoCliente);
//        // Establece otros campos de la reserva...
//
//        // Guarda la reserva en la base de datos
//        reservaRepository.save(reserva);
//
//        return reserva;
//    }

    public Reserva realizarReserva(Long habitacionId, Long clienteId, Date fechaInicio, Date fechaFin) {
        // Validar disponibilidad y crear la reserva
        Habitacion habitacion = habitacionRepository.findById(habitacionId).orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Reserva reserva = new Reserva();
        reserva.setHabitacion(habitacion);
        reserva.setCliente(cliente);
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);  // Puedes tener un enum con estados
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);

        return reservaRepository.save(reserva);
    }




}
