//controller/HeaderController
package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.Reserva;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {

    @GetMapping("/index")
    public String mostrarInicio() {
        return "index";  // Vista correspondiente a "Inicio" (página principal)
    }

    @GetMapping("/habitaciones")
    public String mostrarHabitaciones() {
        return "habitaciones";  // Vista correspondiente a "Habitaciones"
    }

    @GetMapping("/nosotros")
    public String mostrarNosotros() {
        return "nosotros";  // Vista correspondiente a "Nosotros"
    }

    @GetMapping("/contacto")
    public String mostrarContacto() {
        return "contacto";  // Vista correspondiente a "Contacto"
    }


}
