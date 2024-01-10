package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.Habitacion;
import com.hotelsancristobal.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarHabitacion(@RequestBody Habitacion habitacion) {
        habitacionService.registrarHabitacion(habitacion);
        return new ResponseEntity<>("Habitación registrada exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/todas")
    public List<Habitacion> obtenerTodasHabitaciones() {
        return habitacionService.obtenerTodasHabitaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerHabitacionPorId(@PathVariable Long id) {
        return habitacionService.obtenerHabitacionPorId(id)
                .map(habitacion -> new ResponseEntity<>(habitacion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizarHabitacion(@PathVariable Long id, @RequestBody Habitacion nuevaHabitacion) {
        Habitacion habitacionActualizada = habitacionService.actualizarHabitacion(id, nuevaHabitacion);
        if (habitacionActualizada != null) {
            return new ResponseEntity<>(habitacionActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Long id) {
        habitacionService.eliminarHabitacion(id);
        return new ResponseEntity<>("Habitación eliminada exitosamente", HttpStatus.OK);
    }
}
