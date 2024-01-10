package com.hotelsancristobal.repository;

import com.hotelsancristobal.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
//    Cliente findByUsername (String username);
    Cliente findByEmail(String email);


}
