package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.Administrador;
import com.hotelsancristobal.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;



    @GetMapping("/index")
    public String mostrarInicio() {
        return "administrador/index";  // Vista correspondiente a "Inicio" (página principal)
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "administrador/login";  // Vista correspondiente a "Inicio" (página principal)
    }


    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "administrador/registro";
    }

    @PostMapping("/registro")
    public String registrarAdministrador(@ModelAttribute Administrador administrador, RedirectAttributes redirectAttributes) {
        try {
            administradorService.registrarAdministrador(administrador);
            redirectAttributes.addFlashAttribute("successMessage", "Registro exitoso, puedes iniciar sesión");

        }catch (Exception e) {
            // Manejo de errores: puedes personalizar esto según tus necesidades
            redirectAttributes.addFlashAttribute("errorMessage", "Ya existe este usuario registrado con este dni o correo electronico");
        }

        // Redirige al formulario de inicio de sesión después del registro
        return "redirect:/administrador/registro";
    }



}
