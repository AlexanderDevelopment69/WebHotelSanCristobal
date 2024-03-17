package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.*;
import com.hotelsancristobal.service.ClienteService;
import com.hotelsancristobal.service.HabitacionService;
import com.hotelsancristobal.service.ReservaService;
import com.hotelsancristobal.service.TipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @ResponseBody
    public boolean verificarDisponibilidad(@RequestParam("numeroHabitacion") long numeroHabitacion,
                                          @RequestParam("fechaInicio") String fechaInicio,
                                          @RequestParam("fechaFin") String fechaFin) throws ParseException {

        // Fechas de inicio y fin
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date fechaI = sdf.parse(fechaInicio);
        Date fechaF = sdf.parse(fechaFin);




//        if (disponible) {
//            model.addAttribute("successMessage", "Habitación disponible");
//            model.addAttribute("errorMessage", null); // Limpiar el mensaje de error si está disponible
//        } else {
//            model.addAttribute("errorMessage", "Habitación no disponible");
//            model.addAttribute("successMessage", null); // Limpiar el mensaje de éxito si no está disponible
//        }
//
//        return "clientes/reserva_form"; // Redirigir a la misma vista clientes/reserva_form

        // Construir mensaje de respuesta
//        String mensaje;
//        if (disponible) {
//            mensaje = "Habitación disponible";
//        } else {
//            mensaje = "Habitación no disponible";
//        }
//
//        return mensaje;

        return reservaService.verificarDisponibilidad(numeroHabitacion, fechaI, fechaF);

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
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public boolean realizarReserva(Authentication authentication, @RequestParam Long habitacionId,
                                   @RequestParam String fechaInicio,
                                   @RequestParam String fechaFin) throws ParseException {

        // Fechas de inicio y fin
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date fechaI = sdf.parse(fechaInicio);
        Date fechaF = sdf.parse(fechaFin);



         // Obtener información del usuario autenticado
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


//         Verificar si el cliente ha iniciado sesión
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            // El cliente no ha iniciado sesión, manejar la situación según sea necesario
            return false; // Por ejemplo, podrías redirigir a la página de inicio de sesión
        }

        // Verificar si los datos no son nulos
//        if (habitacionId == null || fechaInicio == null || fechaFin == null) {
//            // Datos incompletos, no se puede realizar la reserva
//            return false;
//        }



        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String nombreUsuario = userDetails.getUsername();

        // Ahora puedes utilizar nombreUsuario para obtener información específica del cliente
        Cliente cliente = clienteService.obtenerClientePorEmail(nombreUsuario);

        // Validar disponibilidad y realizar reserva
        boolean disponibilidad = reservaService.verificarDisponibilidad(habitacionId, fechaI, fechaF);

        if (disponibilidad) {
            // Realizar reserva y devolver el resultado
            return reservaService.realizarReserva(habitacionId, cliente.getId(), fechaI, fechaF);
        } else {
            return false; // Si la habitación no está disponible, retornar false
        }

    }


//    @GetMapping("/reservas")
//    public String mostrarReservasCliente(Authentication authentication, Model model) {
//        // Obtener la autenticación actual
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        // Obtener el correo electrónico del usuario autenticado
//        String email = userDetails.getUsername();
//
//        // Lógica para obtener detalles del perfil del cliente
//        Cliente cliente = clienteService.obtenerClientePorEmail(email);
//
//        List<Reserva> reservas = reservaService.obtenerReservasPorIdCliente(cliente.getId());
//        System.out.println(reservas); // Imprimir las reservas en la consola
//        model.addAttribute("reservas", reservas);
//        return "clientes/reservas"; // Nombre de la página HTML o plantilla Thymeleaf para mostrar las reservas
//    }





}
