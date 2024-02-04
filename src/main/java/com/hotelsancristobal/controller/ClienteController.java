package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.Cliente;
import com.hotelsancristobal.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/registro";
    }

    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteService.registrarCliente(cliente);
            redirectAttributes.addFlashAttribute("successMessage", "Registro exitoso, puedes iniciar sesión");

        }catch (Exception e) {
            // Manejo de errores: puedes personalizar esto según tus necesidades
            redirectAttributes.addFlashAttribute("errorMessage", "Ya existe este usuario registrado con este dni o correo electronico");
        }

        // Redirige al formulario de inicio de sesión después del registro
        return "redirect:/clientes/registro";
    }


//    @GetMapping("/login")
//    public String mostrarFormularioLogin() {
//        return "clientes/login";
//    }


//    @PostMapping("/login")
//    public String loginCliente(@RequestParam("email") String email,
//                               @RequestParam("password") String password,
//                               Model model) {
//        // Autenticar al cliente
//        if (clienteService.autenticarCliente(email, password)) {
//            // Redirige a la página de inicio o a donde desees después del inicio de sesión exitoso;
//            return "redirect:/index";
//        } else {
//            // Agrega un mensaje de error si la autenticación falla
//            model.addAttribute("error", "Credenciales inválidas");
//            return "/clientes/login";
//        }
//    }

//    @PostMapping("/clientes/login")
//    public String loginCliente(@RequestParam("email") String email,
//                               @RequestParam("password") String password,
//                               Model model, HttpSession session) {
//        if (clienteService.autenticarCliente(email, password)) {
//            // Obtén al cliente autenticado y guárdalo en la sesión
//            Cliente clienteAutenticado = clienteService.obtenerClientePorEmail(email);
//            session.setAttribute("clienteAutenticado", clienteAutenticado);
//
//            // Redirige a la página de inicio o a donde desees después del inicio de sesión exitoso
//            return "redirect:/index";
//        } else {
//            model.addAttribute("error", "Credenciales inválidas");
//            return "clientes/login";
//        }
//    }


    @GetMapping("/login")
    public String mostrarlogin() {
        return "clientes/login";
    }



    @GetMapping("/lista")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        model.addAttribute("clientes", clientes);
        return "clientes/listaClientes";
    }





    @GetMapping("/perfil")
    public String verPerfil(Authentication authentication, Model model) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Obtener el correo electrónico del usuario autenticado
            String email = userDetails.getUsername();

            // Lógica para obtener detalles del perfil del cliente
            Cliente cliente = clienteService.obtenerClientePorEmail(email);

            // Pasar detalles del cliente al modelo
            model.addAttribute("cliente", cliente);

            return "clientes/perfil";
        }

        // Manejar otros casos según sea necesario (puede redirigir a una página de error)
        return "error";
    }


//    @PostMapping("/editar")
//    public String editarPerfil(@ModelAttribute Cliente cliente,
//                               @RequestParam("password") String password,
//                               Authentication authentication) {
//        // Obtener el email del cliente desde la sesión
//        String email = authentication.getName();
//
//        // Actualizar datos del cliente
//        Cliente clienteExistente = clienteService.obtenerClientePorEmail(email);
//        clienteExistente.setNombre(cliente.getNombre());
//        clienteExistente.setApellido(cliente.getApellido());
//        clienteExistente.setDni(cliente.getDni());
//
////        // Verificar si se proporcionó una nueva contraseña
////        if (!password.isEmpty()) {
////            // Hashear la nueva contraseña y actualizar
////            clienteExistente.setPassword(passwordEncoder.encode(password));
////        }
//
//        // Guardar los cambios en el servicio
//        clienteService.guardarCliente(clienteExistente);
//
//        return "redirect:/clientes/perfil";
//    }


    @PostMapping("/editar")
    public String actualizarDatos(@ModelAttribute("cliente") Cliente cliente, Model model, Authentication authentication, RedirectAttributes redirectAttributes ) {
        // Obtener el nombre de usuario (correo electrónico) del usuario autenticado
        String email = authentication.getName();


        try {
            // Actualizar los datos del cliente en la base de datos
            cliente.setEmail(email); // Asegúrate de que el email no cambie
            clienteService.actualizarDatosCliente(cliente);

            // Mensaje de éxito
//            model.addAttribute("successMessage", "Datos actualizados con éxito");
            redirectAttributes.addFlashAttribute("successMessage", "Datos actualizados con éxito");
        } catch (Exception e) {
            // Manejo de errores: puedes personalizar esto según tus necesidades
//            model.addAttribute("errorMessage", "Error al actualizar");
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar");
        }

//        // Redirigir de nuevo a la página de perfil
//        return "clientes/perfil";
        // Redirigir de nuevo a la página de perfil
        return "redirect:/clientes/perfil";
    }


    @PostMapping("/actualizar-password")
    public String actualizarPassword(@RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword") String newPassword,
                                     Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        // Obtener el nombre de usuario (correo electrónico) del usuario autenticado
        String email = authentication.getName();

        // Verificar la contraseña actual antes de cambiarla
        if (clienteService.verificarPasswordActual(email, oldPassword)) {
            // Actualizar la contraseña del cliente en la base de datos
            clienteService.actualizarPassword(email, newPassword);

            // Mensaje de éxito
//            model.addAttribute("successMessage", "Contraseña cambiada con éxito");
            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("successMessage", "Contraseña cambiada con éxito");
        } else {
            // Mensaje de error si la contraseña actual no es correcta
//            model.addAttribute("errorMessage", "La contraseña actual no es correcta");
            redirectAttributes.addFlashAttribute("errorMessage", "La contraseña actual no es correcta");
        }

        // Redirigir de nuevo a la página de perfil
        return "redirect:/clientes/perfil";
    }



    @GetMapping("/reservas")
    public String verReservas(Model model) {
        // Lógica para mostrar las reservas del cliente
        return "clientes/reservas";
    }


    @GetMapping("/pagos")
    public String verPagos(Model model) {
        // Lógica para mostrar los pagos del cliente
        return "clientes/pagos";
    }


    @GetMapping("/logout")
    public String cerrarSesion(Model model) {
        // Cierra la sesión utilizando Spring Security
//        SecurityContextHolder.clearContext();

        // Puedes agregar lógica adicional antes de redirigir (si es necesario)

        // Redirige a la página de inicio u otra página después de cerrar sesión
        return "redirect:/index";
    }


}
