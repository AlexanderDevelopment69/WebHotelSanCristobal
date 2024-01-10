package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.Administrador;
import com.hotelsancristobal.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarAdministrador(@RequestBody Administrador administrador) {
        Administrador administradorExistente = administradorService.obtenerAdministradorPorEmail(administrador.getEmail());

        if (administradorExistente != null) {
            return new ResponseEntity<>("El administrador ya está registrado", HttpStatus.BAD_REQUEST);
        }

        administradorService.registrarAdministrador(administrador);
        return new ResponseEntity<>("Administrador registrado exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public List<Administrador> obtenerTodosAdministradores() {
        return administradorService.obtenerTodosAdministradores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerAdministradorPorId(@PathVariable Long id) {
        return administradorService.obtenerAdministradorPorId(id)
                .map(administrador -> new ResponseEntity<>(administrador, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdministrador(@PathVariable Long id, @RequestBody Administrador nuevoAdministrador) {
        Administrador administradorActualizado = administradorService.actualizarAdministrador(id, nuevoAdministrador);
        if (administradorActualizado != null) {
            return new ResponseEntity<>(administradorActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAdministrador(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
        return new ResponseEntity<>("Administrador eliminado exitosamente", HttpStatus.OK);
    }
}
