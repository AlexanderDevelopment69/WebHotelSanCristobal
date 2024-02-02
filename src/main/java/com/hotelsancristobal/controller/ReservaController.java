package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.*;
import com.hotelsancristobal.service.ClienteService;
import com.hotelsancristobal.service.HabitacionService;
import com.hotelsancristobal.service.ReservaService;
import com.hotelsancristobal.service.TipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    ReservaService reservaService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    HabitacionService habitacionService;

    @Autowired
    TipoHabitacionService tipoHabitacionService;

//    @GetMapping("/reserva_form")
//    public String mostrarFormularioReserva(Model model) {
//        return "clientes/reserva_form";
//    }

//    @GetMapping("/reserva_form")
//    public String mostrarFormularioReserva(Model model) {
//        // Puedes agregar lógica para cargar datos adicionales necesarios en el formulario
//        List<TipoHabitacion> tiposHabitacion = tipoHabitacionService.getAllTiposHabitacion();  // Suponiendo que tienes un servicio para obtener tipos de habitación
//        model.addAttribute("tiposHabitacion", tiposHabitacion);
//
//        return "clientes/reserva_form";
//    }

    @GetMapping("/reserva_form")
    public String mostrarFormularioReserva(@RequestParam Long tipoId, Model model) {
        // Lógica para obtener las habitaciones del tipo especificado
        List<Habitacion> habitaciones = habitacionService.obtenerHabitacionesPorTipo(tipoId);

        // Agregar las habitaciones al modelo
        model.addAttribute("habitaciones", habitaciones);

        // Puedes agregar más datos al modelo según tus necesidades

        return "clientes/reserva_form";
    }


    @GetMapping("/verificar-disponibilidad")
    public String verificarDisponibilidad(
            @RequestParam("habitacion") Long habitacion,
            @RequestParam("llegada") String llegada,
            @RequestParam("salida") String salida,
            Model model) {

        // Aquí implementa tu lógica para verificar la disponibilidad
        // Puedes acceder a tu servicio (reservaService) y realizar las comprobaciones necesarias
        boolean disponible = reservaService.verificarDisponibilidad(habitacion, llegada, salida);

        // Agregar mensajes al modelo para mostrar en la página
        if (disponible) {
            model.addAttribute("successMessage", "Habitación disponible");
        } else {
            model.addAttribute("errorMessage", "Habitación no disponible");
        }

        // Devolver la vista donde se mostrarán los mensajes
        return "clientes/reserva_form"; // Reemplaza "nombre_de_tu_vista" con el nombre de tu vista Thymeleaf
    }





//    @PostMapping("/reservar")
//    public String reservarHabitacion(@RequestParam Long idHabitacion,
//                                     @RequestParam String fechaInicio,
//                                     @RequestParam String fechaFin,
//                                     @RequestParam String nombreCliente,
//                                     @RequestParam String correoCliente,
//                                     Model model) {
//
//        // Lógica para verificar disponibilidad y realizar reserva
//        if (reservaService.verificarDisponibilidad(idHabitacion, fechaInicio, fechaFin)) {
//            Reserva reserva = reservaService.realizarReserva(idHabitacion, fechaInicio, fechaFin, nombreCliente, correoCliente);
//            model.addAttribute("reserva", reserva);
//            return "confirmacion_reserva";
//        } else {
//            model.addAttribute("error", "La habitación no está disponible en las fechas seleccionadas.");
//            return "error_reserva";
//        }
//    }


    @GetMapping("/form/{habitacionId}")
    public String mostrarFormularioReserva(Model model, @PathVariable Long habitacionId) {
        // Lógica para obtener información de la habitación y mostrar en el formulario
        // Puedes tener un servicio HabitacionService para obtener detalles de la habitación

        model.addAttribute("habitacionId", habitacionId);

        return "clientes/reserva_form";  // Ajusta la ruta según tu estructura
    }

    @PostMapping("/realizarReserva")
    public String realizarReserva(@RequestParam Long habitacionId,
                                  @RequestParam String fechaInicio,
                                  @RequestParam String fechaFin,
                                  Model model) {

        // Obtener información del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String nombreUsuario = userDetails.getUsername();

        // Ahora puedes utilizar nombreUsuario para obtener información específica del cliente
        Cliente cliente = clienteService.obtenerClientePorEmail(nombreUsuario);

        // Validar disponibilidad y realizar reserva
        boolean disponibilidad = reservaService.verificarDisponibilidad(habitacionId, fechaInicio, fechaFin);

        if (disponibilidad) {
            // Crear la instancia de la entidad Reserva
            Reserva reserva = new Reserva();

            // Configurar los campos de la reserva
            // Puedes obtener más detalles de la habitación a través de un servicio
            // Supongo que habitacionService tiene un método similar a obtenerHabitacionPorId
            Habitacion habitacion = habitacionService.obtenerHabitacionPorId(habitacionId);

            reserva.setCliente(cliente);
            reserva.setHabitacion(habitacion);
            reserva.setEstadoReserva(EstadoReserva.PENDIENTE);  // Estado predeterminado
            reserva.setFechaInicio(Date.valueOf(fechaInicio));
            reserva.setFechaFin(Date.valueOf(fechaFin));

            // Guardar la reserva en la base de datos
            // Llama al servicio para realizar la reserva
            reservaService.realizarReserva(habitacionId, cliente.getId(), Date.valueOf(fechaInicio), Date.valueOf(fechaFin));

            return "redirect:/reserva/confirmacion";  // Redirigir a la página de confirmación
        } else {
            // Habitación no disponible, redirigir a una página de error o mostrar un mensaje al usuario
            return "redirect:/reserva/error";  // Redirigir a la página de error
        }
    }


}
