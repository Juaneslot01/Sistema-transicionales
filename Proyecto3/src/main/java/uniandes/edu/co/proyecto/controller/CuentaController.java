package uniandes.edu.co.proyecto.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.services.ClienteService;
import uniandes.edu.co.proyecto.services.CuentaService;

@Controller
public class CuentaController {
    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaRepository cuentaRepository, ClienteRepository clienteRepository, ClienteService clienteService, CuentaService cuentaService) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
    }

    @RequestMapping("/filtro")
    public String index2() {
        return "filtro";
    }


    @GetMapping("/cuentas")
    public String cuentas(Model model) {
        model.addAttribute("cuentas", clienteRepository.obtenerTodasLasCuentas());
        return "cuentas";
    }
    

    @GetMapping("/cuentas/new")
    public String cuentaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNuevo";
    }

    @PostMapping("/cuentas/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta){
        if (clienteRepository.findMaxIdCuenta().isEmpty()){
            cuenta.setNumcuenta(1);
        } else {
            int max = clienteRepository.findMaxIdCuenta().get(0).getmaxIdCuenta();
            cuenta.setNumcuenta(max+1);
        }
        cuenta.setFechacreacion(LocalDate.now());
        clienteService.crearCuenta(cuenta.getId_cliente(), cuenta);
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{numCuenta}/edit")
    public String cuentaEditarForm(@PathVariable("numCuenta") Integer numCuenta, Model model) {
        Cuenta cuenta = clienteRepository.obtenerCuentaPorNumCuenta(numCuenta);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            return "cuentaEditar";
        } else {
            return "redirect:/cuentas";
        }
    }


    @PostMapping("/cuentas/{numCuenta}/edit/save")
    public String cuentaGuardar(@PathVariable("numCuenta") Integer numCuenta, @RequestParam("estado") String estado) {
        cuentaService.updateCuentaEstado(numCuenta, estado);
        return "redirect:/cuentas";
    }


    @GetMapping("/filtrarCuentas")
    public String cuentasFiltrar(Model model, @RequestParam("valorFiltro") String filtro, @RequestParam("filtro") String filtroTipo) {

        if ("tipo".equals(filtroTipo)) {
            model.addAttribute("Cuentas", clienteRepository.darCuentasPorTipo(filtro));
            return "cuentasPorTipo";
        } 
        else if ("saldo".equals(filtroTipo)) {
            String[] saldos = filtro.split("-");
            if (saldos.length == 2) {
                try {
                Integer saldo1 = Integer.parseInt(saldos[0]);
                Integer saldo2 = Integer.parseInt(saldos[1]);

                
                model.addAttribute("Cuentas", clienteRepository.darCuentasPorSaldos(saldo1, saldo2));
                return "cuentasPorTipo";
                } catch (NumberFormatException e) {
                return "error";
            }
        }
        }else if ("fecha".equals(filtroTipo)) {
            Date fecha = convertirStringADate(filtro);
            model.addAttribute("Cuentas", clienteRepository.darCuentasPorFechaCreacion(fecha));
            return "cuentasPorTipo";
        }else if ("fecha_ultimo_movimiento".equals(filtroTipo)) {
            Date fecha1 = convertirStringADate(filtro);
            model.addAttribute("Cuentas", clienteRepository.darCuentasPorFechaUltimaTransaccion(fecha1));
            return "cuentasPorTipo";
        }else if ("cliente".equals(filtroTipo)) {
            model.addAttribute("Cuentas", clienteRepository.darCuentasCliente(Integer.parseInt(filtro)));
            return "cuentasPorTipo";            
        }
        
        return "error";
    }

    @GetMapping("/filtro2")
    public String cuentasFiltrar2() {
        return "filtro2";
    }

    @GetMapping("/filtrarCuentas2")
    public String cuentasFiltrar2(Model model,
                                  @RequestParam("tipoCuenta") String tipoCuenta,
                                  @RequestParam("rangoSaldo") String rangoSaldo,
                                  @RequestParam("fechaCreacion") String fechaCreacion,
                                  @RequestParam("fechaUltimoMovimiento") String fechaUltimoMovimiento,
                                  @RequestParam("numDocumentoCliente") String numDocumentoCliente) {
        
        int[] limitesSaldo = parsearRangoSaldos(rangoSaldo);
        int saldoInferior = limitesSaldo[0];
        int saldoSuperior = limitesSaldo[1];
    
        model.addAttribute("Cuentas", clienteRepository.darCuentasPorFiltro(tipoCuenta, saldoInferior, saldoSuperior, convertirStringADate(fechaCreacion),
        convertirStringADate(fechaUltimoMovimiento), Integer.parseInt(numDocumentoCliente)));
        
        return "cuentasPorTipo";
    }
    
    private int[] parsearRangoSaldos(String rangoSaldo) {
        if (rangoSaldo == null || !rangoSaldo.contains("-")) {
            throw new IllegalArgumentException("Formato de rango de saldos inválido. Debe ser 'saldo1-saldo2'.");
        }
    
        String[] partes = rangoSaldo.split("-");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Formato de rango de saldos inválido. Debe contener exactamente un guion.");
        }
    
        try {
            int saldoInferior = Integer.parseInt(partes[0].trim());
            int saldoSuperior = Integer.parseInt(partes[1].trim());
            return new int[]{saldoInferior, saldoSuperior};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Los valores de saldos deben ser números enteros.");
        }
    }
    


    public static Date convertirStringADate(String fechaString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(fechaString); // Parsear el String a Date
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
