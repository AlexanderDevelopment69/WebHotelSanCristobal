package com.hotelsancristobal.controller;


import com.hotelsancristobal.service.TipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoHabitaciones")
public class TipoHabitacionController {
    @Autowired
    TipoHabitacionService tipoHabitacionService;


    @GetMapping("/listar")
    public String listarTiposHabitaciones(Model model) {
        // Obtener la lista de tipos de habitaciones desde el servicio
        model.addAttribute("tiposHabitaciones", tipoHabitacionService.getAllTiposHabitacion());
        return "habitaciones";  // Nombre de tu plantilla Thymeleaf
    }

}
