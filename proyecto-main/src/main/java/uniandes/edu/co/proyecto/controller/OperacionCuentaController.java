package uniandes.edu.co.proyecto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.service.OperacionCuentaService;

@Controller
public class OperacionCuentaController {

    private final OperacionCuentaRepository operacionCuentaRepository;
    private final OperacionCuentaService operacionCuentaService;



    @Autowired
    public OperacionCuentaController(OperacionCuentaRepository operacionCuentaRepository, OperacionCuentaService operacionCuentaService) {
        this.operacionCuentaRepository = operacionCuentaRepository;
        this.operacionCuentaService = operacionCuentaService;
    }

    @GetMapping("/operacionesCuenta")
    public String operacionesCuenta(Model model) {
        model.addAttribute("operacionesCuenta", operacionCuentaRepository.darOperacionesCuenta());
        return "operacionesCuenta";
}

    @GetMapping("/operacionesCuenta/new")
    public String operacionCuentaForm(Model model) {
        model.addAttribute("operacionCuenta", new OperacionCuenta());
        return "operacionCuentaNuevo";
    }

    @PostMapping("/operacionesCuenta/new/save")
    public String operacionCuentaGuardar(@ModelAttribute OperacionCuenta operacionCuenta, @RequestParam("idPuntoAtencion") Integer idPuntoAtencion, @RequestParam("numCuenta") Integer numCuenta) throws InterruptedException{
        String ret = operacionCuentaService.operacionCuentaGuardar(operacionCuenta, idPuntoAtencion, numCuenta);
        return ret;
    }
}