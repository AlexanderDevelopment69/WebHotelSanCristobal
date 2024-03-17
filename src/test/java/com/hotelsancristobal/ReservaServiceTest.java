package com.hotelsancristobal;
import com.hotelsancristobal.service.ReservaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class ReservaServiceTest {

    @Autowired
    private ReservaService reservaService;

    @Test
    public void testVerificarDisponibilidad() throws ParseException, ParseException {
        // Parámetros de ejemplo
        long numeroHabitacion = 6;

        // Fechas de inicio y fin
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = sdf.parse("2024-02-01");
        Date fechaFin = sdf.parse("2024-02-05");

        // Llamada al método del servicio
        boolean disponible = reservaService.verificarDisponibilidad(numeroHabitacion, fechaInicio, fechaFin);

        // Imprimir el resultado
        System.out.println("La habitación está disponible: " + disponible);
    }
}
