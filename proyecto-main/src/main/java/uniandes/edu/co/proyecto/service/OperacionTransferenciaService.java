package uniandes.edu.co.proyecto.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OperacionTransferencia;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionTransferenciaRepository;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepository;

@Service
public class OperacionTransferenciaService {
    private final OperacionTransferenciaRepository operacionTransferenciaRepository;
    private final CuentaRepository cuentaRepository;
    private final PuntoAtencionRepository puntoAtencionRepository;
    
    public OperacionTransferenciaService(OperacionTransferenciaRepository operacionTransferenciaRepository, CuentaRepository cuentaRepository, PuntoAtencionRepository puntoAtencionRepository) {
        this.operacionTransferenciaRepository = operacionTransferenciaRepository;
        this.cuentaRepository = cuentaRepository;
        this.puntoAtencionRepository = puntoAtencionRepository;
    }
    
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public String OperacionTransferenciaGuardar(OperacionTransferencia operacionTransferencia, Integer idPuntoAtencion, Integer numCuentaOrigen, Integer numCuentaDestino) throws InterruptedException{
        uniandes.edu.co.proyecto.modelo.Cuenta cuentaOrigen = cuentaRepository.darCuenta(numCuentaOrigen);
        uniandes.edu.co.proyecto.modelo.Cuenta cuentaDestino = cuentaRepository.darCuenta(numCuentaDestino);
        uniandes.edu.co.proyecto.modelo.PuntoAtencion puntoAtencion = puntoAtencionRepository.darPuntoAtencion(idPuntoAtencion);
        operacionTransferencia.setCuentaOrigen(cuentaOrigen);
        operacionTransferencia.setCuentaDestino(cuentaDestino);
        operacionTransferencia.setPuntoAtencion(puntoAtencion);
        if (cuentaOrigen.getSaldo()>=operacionTransferencia.getValor() && cuentaOrigen.getEstado().equals("Activa") && cuentaDestino.getEstado().equals("Activa")){
            cuentaRepository.actualizarSaldoCuenta(numCuentaOrigen, cuentaOrigen.getSaldo()-operacionTransferencia.getValor(), LocalDate.now());
            // Aquí podría haber un Thread.sleep para hacer las pruebas de concurrencia
            cuentaRepository.actualizarSaldoCuenta(numCuentaDestino, cuentaDestino.getSaldo()+operacionTransferencia.getValor(), LocalDate.now());
            // Aquí podría haber un Thread.sleep para hacer las pruebas de concurrencia
            operacionTransferenciaRepository.insertarOperacionTransferencia(Contador.generarIdOperacionTransferencia(), operacionTransferencia.getValor(), operacionTransferencia.getFecha(), numCuentaOrigen, numCuentaDestino, idPuntoAtencion);
            // Aquí podría haber un Thread.sleep para hacer las pruebas de concurrencia
        }
        else throw new IllegalArgumentException("La cuenta origen no tiene suficiente saldo");
        
        return "redirect:/operacionesTransferencia";
    }
    
}
