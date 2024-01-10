package com.hotelsancristobal.controller;

import com.hotelsancristobal.model.Pago;
import com.hotelsancristobal.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarPago(@RequestBody Pago pago) {
        pagoService.registrarPago(pago);
        return new ResponseEntity<>("Pago registrado exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public List<Pago> obtenerTodosPagos() {
        return pagoService.obtenerTodosPagos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable Long id) {
        return pagoService.obtenerPagoPorId(id)
                .map(pago -> new ResponseEntity<>(pago, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(@PathVariable Long id, @RequestBody Pago nuevoPago) {
        Pago pagoActualizado = pagoService.actualizarPago(id, nuevoPago);
        if (pagoActualizado != null) {
            return new ResponseEntity<>(pagoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
        return new ResponseEntity<>("Pago eliminado exitosamente", HttpStatus.OK);
    }
}
