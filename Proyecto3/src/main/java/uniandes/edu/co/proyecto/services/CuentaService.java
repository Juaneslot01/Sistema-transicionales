package uniandes.edu.co.proyecto.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;

@Service
public class CuentaService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public CuentaService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void updateCuentaEstado(int numCuenta, String estado) {
        Cliente cliente = clienteRepository.findByNumCuenta(numCuenta);
        if (cliente != null) {
            for (int i = 0; i < cliente.getCuentas().size(); i++) {
                if (cliente.getCuentas().get(i).getNumcuenta() == numCuenta){
                    cliente.getCuentas().get(i).setEstado(estado);
                    clienteRepository.save(cliente);
                    break;
                }
            }
        }
    }

    public void updateCuentaSaldo(int numCuenta, int saldo) {
        Cliente cliente = clienteRepository.findByNumCuenta(numCuenta);
        if (cliente != null) {
            for (int i = 0; i < cliente.getCuentas().size(); i++) {
                if (cliente.getCuentas().get(i).getNumcuenta() == numCuenta){
                    cliente.getCuentas().get(i).setSaldo(saldo);
                    cliente.getCuentas().get(i).setFechaultimatransaccion(LocalDate.now());
                    clienteRepository.save(cliente);
                    break;
                }
            }
        }
    }


    
}
