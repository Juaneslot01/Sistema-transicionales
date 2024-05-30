package uniandes.edu.co.proyecto.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionRepository;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepository;

import java.time.LocalDate;


@Service
public class OperacionService {
    private final OperacionRepository OperacionRepository;
    private final OficinaRepository oficinaRepository;
    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;    
    private final PuntoAtencionRepository puntoAtencionRepository;
    private final CuentaService cuentaService;
    
    public OperacionService(CuentaRepository cuentaRepository, OperacionRepository OperacionRepository, PuntoAtencionRepository puntoAtencionRepository, ClienteRepository clienteRepository, 
    OficinaRepository oficinaRepository, CuentaService cuentaService) {
        this.OperacionRepository = OperacionRepository;
        this.cuentaRepository = cuentaRepository;
        this.puntoAtencionRepository = puntoAtencionRepository;
        this.clienteRepository = clienteRepository;
        this.oficinaRepository = oficinaRepository;
        this.cuentaService = cuentaService;
    }

    public String OperacionGuardar(Operacion Operacion, Integer idPuntoAtencion, Integer numCuenta, Integer numCuentaDestino) throws InterruptedException{
        uniandes.edu.co.proyecto.modelo.Cuenta cuenta = clienteRepository.obtenerCuentaPorNumCuenta(numCuenta);
        uniandes.edu.co.proyecto.modelo.PuntoAtencion puntoAtencion = oficinaRepository.darPuntoAtencion(idPuntoAtencion);
        Operacion.setNumcuentaprincipal(cuenta.getNumcuenta());
        Operacion.setIdpuntoatencion(puntoAtencion.getid());
        boolean terminado = false;
        if(cuenta.getEstado().equals("Inactiva")) throw new IllegalArgumentException("La cuenta está inactiva");
        if (Operacion.getTipo().equals("Retiro")){
            Operacion.setNumcuentadestino(null);
            if (cuenta.getSaldo()>=Operacion.getValor()){
                cuentaService.updateCuentaSaldo(numCuenta, cuenta.getSaldo()-Operacion.getValor());
                terminado = true;
            }
            else throw new IllegalArgumentException("La cuenta no tiene suficiente saldo");
        }
        else if (Operacion.getTipo().equals("Consignación")){
            Operacion.setNumcuentadestino(null);
            cuentaService.updateCuentaSaldo(numCuenta, cuenta.getSaldo()+Operacion.getValor());
            terminado = true;
        }
        if (Operacion.getTipo().equals("Transferencia") && !terminado){
            if (numCuentaDestino == null || numCuentaDestino == 0 || numCuentaDestino == cuenta.getNumcuenta() || clienteRepository.obtenerCuentaPorNumCuenta(numCuentaDestino) == null){
                throw new IllegalArgumentException("La cuenta destino no es válida");
            }
            uniandes.edu.co.proyecto.modelo.Cuenta cuentaDestino = clienteRepository.obtenerCuentaPorNumCuenta(numCuentaDestino);
            if (cuenta.getSaldo()>=Operacion.getValor() && cuentaDestino.getEstado().equals("Activa")){
                cuentaService.updateCuentaSaldo(numCuenta, cuenta.getSaldo()-Operacion.getValor());
                cuentaService.updateCuentaSaldo(cuentaDestino.getNumcuenta(), cuentaDestino.getSaldo()+Operacion.getValor());
                cuentaDestino.setFechaultimatransaccion(LocalDate.now());
            }
            else throw new IllegalArgumentException("La cuenta origen no tiene suficiente saldo o la cuenta destino está inactiva");
        }

        cuenta.setFechaultimatransaccion(LocalDate.now());
        return "redirect:/operacionesCuenta";
    }
}
