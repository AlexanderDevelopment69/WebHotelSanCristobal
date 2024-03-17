//controller/HeaderController
package com.hotelsancristobal.controller;

import com.hotelsancristobal.service.TipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {
    @Autowired
    TipoHabitacionService tipoHabitacionService;


    @GetMapping("/index")
    public String mostrarInicio(Model model) {
        // Obtener la lista de tipos de habitaciones desde el servicio
        model.addAttribute("tiposHabitaciones", tipoHabitacionService.getAllTiposHabitacion());
        return "index";  // Vista correspondiente a "Inicio" (página principal)
    }

//    @GetMapping("/habitaciones")
//    public String mostrarHabitaciones() {
//        return "habitaciones";  // Vista correspondiente a "Habitaciones"
//    }
//
    @GetMapping("/habitaciones")
    public String listarTiposHabitaciones(Model model) {
        // Obtener la lista de tipos de habitaciones desde el servicio
        model.addAttribute("tiposHabitaciones", tipoHabitacionService.getAllTiposHabitacion());
        return "habitaciones";  // Nombre de tu plantilla Thymeleaf
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
