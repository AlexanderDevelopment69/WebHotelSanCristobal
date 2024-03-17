package com.hotelsancristobal.repository;


import com.hotelsancristobal.model.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Long> {
    // Puedes agregar consultas específicas si es necesario

//    BigDecimal findTipoHabitacionByIdTipoHabitacion(long idTipoHabitacion);

//    BigDecimal findPrecioByIdHabitacion(Long idHabitacion);

}
