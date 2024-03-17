package com.hotelsancristobal.repository;

import com.hotelsancristobal.model.Cliente;
import com.hotelsancristobal.model.EstadoReserva;
import com.hotelsancristobal.model.Habitacion;
import com.hotelsancristobal.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Autowired
    HabitacionRepository habitacionRepository = null; // Repositorio para acceder a las habitaciones

    @Autowired
    ClienteRepository clienteRepository = null; // Repositorio para acceder a los clientes


    // Puedes agregar métodos adicionales según sea necesario

//    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN false ELSE true END " +
//            "FROM Reserva r " +
//            "WHERE r.habitacion.numeroHabitacion = :numeroHabitacion " +
//            "AND r.fechaInicio <= :fechaFin " +
//            "AND r.fechaFin >= :fechaInicio")
//    boolean verificarDisponibilidad(long numeroHabitacion, Date fechaInicio, Date fechaFin);


    @Query("SELECT CASE " +
            "WHEN :fechaInicio >= :fechaFin THEN false " +
            "WHEN COUNT(r) > 0 THEN false " +
            "ELSE true " +
            "END " +
            "FROM Reserva r " +
            "WHERE r.habitacion.idHabitacion= :idHabitacion " +
            "AND :fechaInicio BETWEEN r.fechaInicio AND r.fechaFin")
    boolean verificarDisponibilidad(Long idHabitacion, Date fechaInicio, Date fechaFin
    );


    // Método para reservar una habitación para las fechas especificadas
    @Transactional
    default boolean reservarHabitacion(long idHabitacion, Date fechaInicio, Date fechaFin, long idCliente) {
        Reserva reserva = new Reserva();
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);

        // Obtener la habitación y el cliente correspondientes a los IDs proporcionados
        Habitacion habitacion = habitacionRepository.findById(idHabitacion).orElse(null);
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);

        // Verificar si se encontraron la habitación y el cliente
        if (habitacion != null && cliente != null) {
            reserva.setHabitacion(habitacion);
            reserva.setCliente(cliente);

            save(reserva); // Guardar la reserva en la base de datos
            return true; // Devolver true si se realizó la reserva con éxito
        }
        return false; // Devolver false si la habitación no está disponible o no se encontró el cliente
    }


    @Query("SELECT " +
            "r.idReserva AS numeroReserva, " +
            "th.nombreTipo AS tipoHabitacion, " +
            "h.numeroHabitacion AS numeroHabitacion, " +
            "th.precio AS precio, " +
            "r.fechaInicio AS fechaLlegada, " +
            "r.fechaFin AS fechaSalida, " +
            "r.estadoReserva AS estado " +
            "FROM " +
            "Reserva r " +
            "INNER JOIN r.habitacion h " +
            "INNER JOIN h.tipoHabitacion th " +
            "WHERE " +
            "r.cliente.id = :clienteId")
    List<Object[]> obtenerDetallesReservasCliente(@Param("clienteId") Long clienteId);



    List<Reserva> findAllByClienteId(Long idCliente);

}
