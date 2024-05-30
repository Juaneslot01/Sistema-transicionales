package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.repositorio.OperacionRepository;
import uniandes.edu.co.proyecto.services.OperacionService;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;

@Controller
public class OperacionController {

    private final OperacionRepository OperacionRepository;
    private final OperacionService OperacionService;
    private final ClienteRepository clienteRepository;

    @GetMapping("/extracto")
    public String extracto(Model model) {
        return "extracto";
    }

    @Autowired
    public OperacionController(OperacionRepository OperacionRepository, OperacionService OperacionService, ClienteRepository clienteRepository) {
        this.OperacionRepository = OperacionRepository;
        this.OperacionService = OperacionService;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/operacionesTransferencia")
    public String operacionesTransferencia(Model model) {
        model.addAttribute("operacionesTransferencia", OperacionRepository.darOperacionesTransferencia());
        return "operacionesTransferencia";
}

    @GetMapping("/operacionesTransferencia/new")
    public String OperacionForm(Model model) {
        model.addAttribute("operacionTransferencia", new Operacion());
        return "operacionTransferenciaNuevo";
    }

    @PostMapping("/operacionesTransferencia/new/save")
    public String OperacionGuardar(@ModelAttribute Operacion Operacion, @RequestParam("idpuntoatencion") Integer idPuntoAtencion, @RequestParam("numcuentaprincipal") Integer numCuentaOrigen, 
    @RequestParam("numcuentadestino") Object numCuentaDestino) throws InterruptedException{
        try {
            if (numCuentaDestino == null) {
                numCuentaDestino = -1;
            } else {
                numCuentaDestino = Integer.parseInt(numCuentaDestino.toString());
            }
        } catch (NumberFormatException e) {
            numCuentaDestino = -1;
        }
        OperacionService.OperacionGuardar(Operacion, idPuntoAtencion, numCuentaOrigen, Integer.parseInt(numCuentaDestino.toString()));
        if (OperacionRepository.findTop1ByOrderByNumerodocumentoDesc().isEmpty()) Operacion.setId(1);
        else Operacion.setId(OperacionRepository.findTop1ByOrderByNumerodocumentoDesc().iterator().next().getId() + 1);
        OperacionRepository.save(Operacion);
        return "redirect:/operacionesTransferencia";
    }

    @GetMapping("/generar-extracto")
    public String extracto(Model model, @RequestParam("numeroCuenta") Integer numCuenta, @RequestParam("fecha") String fecha) {
        System.out.println("numeroCuenta: " + numCuenta);
        Cuenta cuenta = clienteRepository.obtenerCuentaPorNumCuenta(numCuenta);
        Integer saldo = cuenta.getSaldo();
        List<Operacion> operacionesCuenta = OperacionRepository.darOperacionCuenta(numCuenta, fecha);
        model.addAttribute("saldoInicial", saldo);
        model.addAttribute("cuenta", cuenta);
        model.addAttribute("operacionesCuentas", operacionesCuenta);
        

        for (Operacion operacionCuenta : operacionesCuenta) {
            if (operacionCuenta.getTipo().equals("Retiro")) {
                saldo += operacionCuenta.getValor();
            } else if (operacionCuenta.getTipo().equals("Consignacion")) {
                saldo -= operacionCuenta.getValor();
            }
            else if (operacionCuenta.getTipo().equals("Transferencia")) {
                saldo += operacionCuenta.getValor();
            }
        }
        model.addAttribute("saldofinal", saldo);


        return "extractogeneral";
    }

}
