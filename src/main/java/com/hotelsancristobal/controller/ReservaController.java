package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.Reserva;
import com.hotelsancristobal.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarReserva(@RequestBody Reserva reserva) {
        reservaService.registrarReserva(reserva);
        return new ResponseEntity<>("Reserva registrada exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/todas")
    public List<Reserva> obtenerTodasReservas() {
        return reservaService.obtenerTodasReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable Long id) {
        return reservaService.obtenerReservaPorId(id)
                .map(reserva -> new ResponseEntity<>(reserva, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva nuevaReserva) {
        Reserva reservaActualizada = reservaService.actualizarReserva(id, nuevaReserva);
        if (reservaActualizada != null) {
            return new ResponseEntity<>(reservaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return new ResponseEntity<>("Reserva eliminada exitosamente", HttpStatus.OK);
    }
}
