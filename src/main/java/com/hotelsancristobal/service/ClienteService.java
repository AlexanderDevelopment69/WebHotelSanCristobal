package com.hotelsancristobal.service;

import com.hotelsancristobal.dto.ClienteDTO;
import com.hotelsancristobal.model.Cliente;
import com.hotelsancristobal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

//    public Cliente registrarCliente(Cliente cliente) {
//        // Cifra la contraseña antes de guardarla
//        String passwordHash = BCrypt.hashpw(cliente.getPassword(), BCrypt.gensalt());
//        cliente.setPassword(passwordHash);
//
//
//
//        // Lógica para validar y guardar el cliente
//        return clienteRepository.save(cliente);
//    }


    public Cliente registrarCliente(Cliente cliente) {
//         Cifra la contraseña antes de guardarla
        String passwordHash = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(passwordHash);

//        Cliente cliente = new Cliente(clienteDTO.getNombre(), clienteDTO.getApellido(), clienteDTO.getDni(), clienteDTO.getUsername(), passwordEncoder.encode(clienteDTO.getPassword()));

        // Lógica para validar y guardar el cliente
        return clienteRepository.save(cliente);
    }


    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente obtenerClientePorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Cliente actualizarCliente(Long id, Cliente nuevoCliente) {
        if (clienteRepository.existsById(id)) {
            nuevoCliente.setId(id);
            return clienteRepository.save(nuevoCliente);
        } else {
            // Manejar caso en el que el cliente no existe
            return null;
        }
    }

    public void guardarCliente(Cliente cliente) {
//        String password = cliente.getPassword();
//        // Verificar si se proporcionó una nueva contraseña
//        if (!password.isEmpty()) {
//            passwordEncoder.encode(password);
//        }
        clienteRepository.save(cliente);
    }

    public void actualizarDatosCliente(Cliente cliente) {
        // Comprueba si el cliente existe
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteExistente != null) {
            // Actualiza los campos específicos del cliente
            clienteExistente.setNombres(cliente.getNombres());
            clienteExistente.setApellidos(cliente.getApellidos());
            clienteExistente.setDni(cliente.getDni());

            // Puedes agregar más campos según sea necesario

            // Guarda los cambios en la base de datos
            clienteRepository.save(clienteExistente);
        }
    }

    public boolean verificarPasswordActual(String email, String oldPassword) {
        // Lógica para verificar si la contraseña actual es correcta
        Cliente cliente = clienteRepository.findByEmail(email);
        return passwordEncoder.matches(oldPassword, cliente.getPassword());
    }

    public void actualizarPassword(String email, String newPassword) {
        // Lógica para actualizar la contraseña del cliente
        Cliente cliente = clienteRepository.findByEmail(email);
        cliente.setPassword(passwordEncoder.encode(newPassword));
        clienteRepository.save(cliente);
    }



    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

//    public Cliente obtenerClientePorEmail(String email) {
//        return clienteRepository.findByEmail(email);
//    }
//
//    public boolean autenticarCliente(String email, String password) {
//        Cliente cliente = clienteRepository.findByEmail(email);
//        // Verificar que el cliente existe y comparar la contraseña con BCrypt
//        return cliente != null && BCrypt.checkpw(password, cliente.getPassword());
//    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserDetailsCustom userDetailsCustom = getUserDetailsCustom(email);
//
//        if(ObjectUtils.isEmpty(userDetailsCustom)){
//            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
//        }
//        return userDetailsCustom;
//    }
//
//    private UserDetailsCustom getUserDetailsCustom(String email){
//        Cliente cliente = clienteRepository.findByEmail(email);
//
//        if(ObjectUtils.isEmpty(cliente)){
//            throw new RuntimeException();
//        }
//
//        return new UserDetailsCustom(
//                cliente.getEmail(),
//                cliente.getPassword()
//        );
//    }


//    public Cliente autenticarCliente(String email, String password) {
//        Cliente cliente = clienteRepository.findByEmail(email);
//        if (cliente != null && BCrypt.checkpw(password, cliente.getPassword())) {
//            cliente.setAutenticado(true);
//            return clienteRepository.save(cliente);
//        }
//        return null;
//    }


}
