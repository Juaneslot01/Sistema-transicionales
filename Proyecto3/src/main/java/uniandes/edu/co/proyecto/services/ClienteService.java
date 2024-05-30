package uniandes.edu.co.proyecto.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void crearCuenta(Integer idCliente, Cuenta nuevaCuenta) {

        Cliente cliente = clienteRepository.darClientePorNumeroDocumento(idCliente);
        if (cliente != null) {
            if (cliente.getCuentas() == null) {
                cliente.setCuentas(new ArrayList<>());
            }
            cliente.getCuentas().add(nuevaCuenta);
            clienteRepository.save(cliente);
        }
    }
    
}
