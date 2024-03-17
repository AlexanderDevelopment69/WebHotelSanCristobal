package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Cliente;
import com.hotelsancristobal.model.EstadoReserva;
import com.hotelsancristobal.model.Habitacion;
import com.hotelsancristobal.model.Reserva;
import com.hotelsancristobal.repository.ClienteRepository;
import com.hotelsancristobal.repository.HabitacionRepository;
import com.hotelsancristobal.repository.ReservaRepository;
import com.hotelsancristobal.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ReservaService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;


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


    public boolean verificarDisponibilidad(long numeroHabitacion, Date fechaInicio, Date fechaFin) {

        return reservaRepository.verificarDisponibilidad(numeroHabitacion, fechaInicio, fechaFin);
    }

    public List<Reserva> obtenerReservasPorIdCliente(Long idCliente) {
        return reservaRepository.findAllByClienteId(idCliente);
    }


//    public Reserva realizarReserva(Long habitacionId, Long clienteId, Date fechaInicio, Date fechaFin) {
//        // Validar disponibilidad y crear la reserva
//        Habitacion habitacion = habitacionRepository.findById(habitacionId).orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
//        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
//
//        Reserva reserva = new Reserva();
//        reserva.setHabitacion(habitacion);
//        reserva.setCliente(cliente);
//        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);  // Puedes tener un enum con estados
//        reserva.setFechaInicio(fechaInicio);
//        reserva.setFechaFin(fechaFin);
//
//        return reservaRepository.save(reserva);
//    }

    public boolean realizarReserva(Long habitacionId, Long clienteId, Date fechaInicio, Date fechaFin) {
        // Verificar si la habitación existe
        Habitacion habitacion = habitacionRepository.findById(habitacionId).orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        // Verificar si el cliente existe
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        // Aquí podrías realizar la lógica necesaria para crear la reserva
        Reserva reserva = new Reserva();


        // Calcular la diferencia en días
//        long diferenciaEnMilisegundos = fechaFin.getTime() - fechaInicio.getTime();
//        long diasDiferencia = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);



        // Calcular el monto total

        // Configurar los campos de la reserva
        reserva.setHabitacion(habitacion);
        reserva.setCliente(cliente);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE); // Estado predeterminado
//        reserva.setMontoReserva(montoReserva);

        // Guardar la reserva en la base de datos
        reservaRepository.save(reserva);

        // Devolver true si la reserva se realizó con éxito
        return true;
    }


}
