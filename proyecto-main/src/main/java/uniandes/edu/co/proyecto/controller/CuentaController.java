package uniandes.edu.co.proyecto.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.OperacionCuenta;
import uniandes.edu.co.proyecto.modelo.OperacionTransferencia;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionTransferenciaRepository;
import uniandes.edu.co.proyecto.service.Contador;
import uniandes.edu.co.proyecto.service.CuentaService;

@Controller
public class CuentaController {
    private final CuentaRepository cuentaRepository;
    private final OficinaRepository oficinaRepository;
    private final ClienteRepository clienteRepository;
    private final OperacionCuentaRepository operacionCuentaRepository;
    private final OperacionTransferenciaRepository operacionTransferenciaRepository;
    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaRepository cuentaRepository, OficinaRepository oficinaRepository, ClienteRepository clienteRepository, 
    OperacionCuentaRepository operacionCuentaRepository, OperacionTransferenciaRepository operacionTransferenciaRepository, CuentaService cuentaService) {
        this.cuentaService = cuentaService;
        this.cuentaRepository = cuentaRepository;
        this.oficinaRepository = oficinaRepository;
        this.clienteRepository = clienteRepository;
        this.operacionCuentaRepository = operacionCuentaRepository;
        this.operacionTransferenciaRepository = operacionTransferenciaRepository;
    }

    @GetMapping("/get-operaciones")
    public String getOperaciones(Model model, @RequestParam("numeroCuenta") Integer numCuenta) throws InterruptedException {
        System.out.println("numeroCuenta: " + numCuenta);
        Cuenta cuenta = cuentaRepository.darCuenta(numCuenta);
        Map<String, Collection<Object>> operaciones = cuentaService.darOperaciones(numCuenta);

        model.addAttribute("cuenta", cuenta);
        model.addAttribute("operacionesCuentas", operaciones.get("OperacionesCuenta"));
        model.addAttribute("operacionesTransferencias", operaciones.get("OperacionesTransferencia"));

        model.addAttribute("operacionesCuentas2", operaciones.get("OperacionesCuenta2"));
        model.addAttribute("operacionesTransferencias2", operaciones.get("OperacionesTransferencia2"));


        return "extractogeneral2";
    }

    @GetMapping("/get-operaciones-read-commited")
    public String getOperacionesReadCommited(Model model, @RequestParam("numeroCuenta") Integer numCuenta) throws InterruptedException {
        System.out.println("numeroCuenta: " + numCuenta);
        Cuenta cuenta = cuentaRepository.darCuenta(numCuenta);
        Map<String, Collection<Object>> operaciones = cuentaService.darOperacionesReadCommited(numCuenta);

        model.addAttribute("cuenta", cuenta);
        model.addAttribute("operacionesCuentas", operaciones.get("OperacionesCuenta"));
        model.addAttribute("operacionesTransferencias", operaciones.get("OperacionesTransferencia"));

        model.addAttribute("operacionesCuentas2", operaciones.get("OperacionesCuenta2"));
        model.addAttribute("operacionesTransferencias2", operaciones.get("OperacionesTransferencia2"));


        return "extractogeneral2";
    }

    @GetMapping("/generar-extracto")
    public String extracto(Model model, @RequestParam("numeroCuenta") Integer numCuenta) {
        System.out.println("numeroCuenta: " + numCuenta);
        Cuenta cuenta = cuentaRepository.darCuenta(numCuenta);
        Integer saldo = cuenta.getSaldo();
        Collection<OperacionCuenta> operacionesCuenta = operacionCuentaRepository.darOperacionesUnaCuenta(numCuenta);
        Collection<OperacionTransferencia> operacionesTransferencia = operacionTransferenciaRepository.darOperacionesTransferenciaCuenta(numCuenta);
        model.addAttribute("saldoInicial", saldo);
        model.addAttribute("cuenta", cuenta);
        model.addAttribute("operacionesCuentas", operacionesCuenta);
        model.addAttribute("operacionesTransferencias", operacionesTransferencia);

        for (OperacionCuenta operacionCuenta : operacionesCuenta) {
            if (operacionCuenta.getTipoOperacion().equals("Retiro")) {
                saldo += operacionCuenta.getValor();
            } else {
                saldo -= operacionCuenta.getValor();
            }
        }

        for (OperacionTransferencia operacionTransferencia : operacionesTransferencia) {
            if (operacionTransferencia.getCuentaOrigen().getNumCuenta().equals(numCuenta)) {
                saldo += operacionTransferencia.getValor();
            } else {
                saldo -= operacionTransferencia.getValor();
            }
        }

        model.addAttribute("saldofinal", saldo);


        return "extractogeneral";
    }

    @GetMapping("/cuentas")
    public String cuentas(Model model) {
        model.addAttribute("cuentas", cuentaRepository.darCuentas());
        return "cuentas";
    }
    

    @GetMapping("/cuentas/new")
    public String cuentaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNuevo";
    }

    @PostMapping("/cuentas/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta, @RequestParam("numdoccliente") Integer numdoc, @RequestParam("idoficina") Integer idoficina){
        uniandes.edu.co.proyecto.modelo.Oficina oficina = oficinaRepository.darOficina(idoficina);
        uniandes.edu.co.proyecto.modelo.Cliente cliente = clienteRepository.darCliente(numdoc);
        cuenta.setOficina(oficina);
        cuenta.setCliente(cliente);
        cuentaRepository.insertarCuenta(Contador.generarNumCuenta(), cuenta.getTipoCuenta(), cuenta.getSaldo(), cuenta.getEstado(), cuenta.getFechaUltimaTransaccion(), numdoc, LocalDate.now(), idoficina);
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{numCuenta}/edit")
    public String cuentaEditarForm(@PathVariable("numCuenta") Integer numCuenta, Model model) {
        Cuenta cuenta = cuentaRepository.darCuenta(numCuenta);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            return "cuentaEditar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/cuentas/{numCuenta}/edit1/save")
    public String cuentaEditarGuardarSaldo(@PathVariable("numCuenta") Integer numCuenta, @ModelAttribute Cuenta cuenta) {
        cuentaRepository.actualizarSaldoCuenta(numCuenta, cuenta.getSaldo(), cuenta.getFechaUltimaTransaccion());
        return "redirect:/cuentas";
    }

    @PostMapping("/cuentas/{numCuenta}/edit/save")
    public String cuentaEditarGuardarEstado(@PathVariable("numCuenta") Integer numCuenta, @ModelAttribute Cuenta cuenta) {
        cuentaRepository.actualizarEstadoCuenta(numCuenta, cuenta.getEstado());
        return "redirect:/cuentas";
    }

}
