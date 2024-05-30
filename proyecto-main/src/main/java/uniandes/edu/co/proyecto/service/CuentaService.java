package uniandes.edu.co.proyecto.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;
import uniandes.edu.co.proyecto.modelo.OperacionTransferencia;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionTransferenciaRepository;

@Service
public class CuentaService {

    private final OperacionCuentaRepository operacionCuentaRepository;
    private final OperacionTransferenciaRepository  operacionTransferenciaRepository;

    public CuentaService(CuentaRepository cuentaRepository, OperacionCuentaRepository operacionCuentaRepository, OperacionTransferenciaRepository operacionTransferenciaRepository) {
        this.operacionCuentaRepository = operacionCuentaRepository;
        this.operacionTransferenciaRepository = operacionTransferenciaRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Map<String, Collection<Object>> darOperaciones(int numcuenta) throws InterruptedException {
        Collection<OperacionCuenta> operacionesCuenta = operacionCuentaRepository.darOperacionesUnaCuenta(numcuenta);
        Collection<OperacionTransferencia> operacionesTransferencia = operacionTransferenciaRepository.darOperacionesTransferenciaCuenta(numcuenta);
    
        Map<String, Collection<Object>> operacionesMap = new HashMap<>();
        operacionesMap.put("OperacionesCuenta", new ArrayList<>(operacionesCuenta));
        operacionesMap.put("OperacionesTransferencia", new ArrayList<>(operacionesTransferencia));

        Thread.sleep(10000);
        
        Collection<OperacionCuenta> operacionesCuenta2 = operacionCuentaRepository.darOperacionesUnaCuenta(numcuenta);
        Collection<OperacionTransferencia> operacionesTransferencia2 = operacionTransferenciaRepository.darOperacionesTransferenciaCuenta(numcuenta);

        operacionesMap.put("OperacionesCuenta2", new ArrayList<>(operacionesCuenta2));
        operacionesMap.put("OperacionesTransferencia2", new ArrayList<>(operacionesTransferencia2));
        return operacionesMap;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Map<String, Collection<Object>> darOperacionesReadCommited(int numcuenta) throws InterruptedException {
        Collection<OperacionCuenta> operacionesCuenta = operacionCuentaRepository.darOperacionesUnaCuenta(numcuenta);
        Collection<OperacionTransferencia> operacionesTransferencia = operacionTransferenciaRepository.darOperacionesTransferenciaCuenta(numcuenta);
    
        Map<String, Collection<Object>> operacionesMap = new HashMap<>();
        operacionesMap.put("OperacionesCuenta", new ArrayList<>(operacionesCuenta));
        operacionesMap.put("OperacionesTransferencia", new ArrayList<>(operacionesTransferencia));

        Thread.sleep(10000);
        
        Collection<OperacionCuenta> operacionesCuenta2 = operacionCuentaRepository.darOperacionesUnaCuenta(numcuenta);
        Collection<OperacionTransferencia> operacionesTransferencia2 = operacionTransferenciaRepository.darOperacionesTransferenciaCuenta(numcuenta);

        operacionesMap.put("OperacionesCuenta2", new ArrayList<>(operacionesCuenta2));
        operacionesMap.put("OperacionesTransferencia2", new ArrayList<>(operacionesTransferencia2));
        return operacionesMap;
    }

    
}
