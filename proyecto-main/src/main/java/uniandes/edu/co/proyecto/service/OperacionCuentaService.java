package uniandes.edu.co.proyecto.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.OperacionCuenta;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepository;

import java.time.LocalDate;


@Service
public class OperacionCuentaService {
    private final OperacionCuentaRepository operacionCuentaRepository;
    private final CuentaRepository cuentaRepository;    
    private final PuntoAtencionRepository puntoAtencionRepository;
    
    public OperacionCuentaService(CuentaRepository cuentaRepository, OperacionCuentaRepository operacionCuentaRepository, PuntoAtencionRepository puntoAtencionRepository) {
        this.operacionCuentaRepository = operacionCuentaRepository;
        this.cuentaRepository = cuentaRepository;
        this.puntoAtencionRepository = puntoAtencionRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public String operacionCuentaGuardar(OperacionCuenta operacionCuenta, Integer idPuntoAtencion, Integer numCuenta) throws InterruptedException{
        uniandes.edu.co.proyecto.modelo.Cuenta cuenta = cuentaRepository.darCuenta(numCuenta);
        uniandes.edu.co.proyecto.modelo.PuntoAtencion puntoAtencion = puntoAtencionRepository.darPuntoAtencion(idPuntoAtencion);
        operacionCuenta.setCuenta(cuenta);
        operacionCuenta.setPuntoAtencion(puntoAtencion);
        if(cuenta.getEstado().equals("Inactiva")) throw new IllegalArgumentException("La cuenta está inactiva");
        if (operacionCuenta.getTipoOperacion().equals("Retiro")){
            if (cuenta.getSaldo()>=operacionCuenta.getValor()){
                cuentaRepository.actualizarSaldoCuenta(numCuenta, cuenta.getSaldo()-operacionCuenta.getValor(), LocalDate.now());
                // Aquí podría haber un Thread.sleep para hacer las pruebas de concurrencia
                operacionCuentaRepository.insertarOperacionCuenta(Contador.generarIdOperacionCuenta(), operacionCuenta.getTipoOperacion(), operacionCuenta.getValor(), operacionCuenta.getFecha(), idPuntoAtencion, numCuenta);
            }
            else throw new IllegalArgumentException("La cuenta no tiene suficiente saldo");
        }
        else{
            cuentaRepository.actualizarSaldoCuenta(numCuenta, cuenta.getSaldo()+operacionCuenta.getValor(), LocalDate.now());
            // Aquí podría haber un Thread.sleep para hacer las pruebas de concurrencia

            operacionCuentaRepository.insertarOperacionCuenta(Contador.generarIdOperacionCuenta(), operacionCuenta.getTipoOperacion(), operacionCuenta.getValor(), operacionCuenta.getFecha(), idPuntoAtencion, numCuenta);
        }
        return "redirect:/operacionesCuenta";
    }
}
