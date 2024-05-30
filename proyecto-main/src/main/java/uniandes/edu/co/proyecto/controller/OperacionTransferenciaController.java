package uniandes.edu.co.proyecto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.OperacionTransferencia;
import uniandes.edu.co.proyecto.repositorio.OperacionTransferenciaRepository;
import uniandes.edu.co.proyecto.service.OperacionTransferenciaService;

@Controller
public class OperacionTransferenciaController {

    private final OperacionTransferenciaRepository operacionTransferenciaRepository;
    private final OperacionTransferenciaService operacionTransferenciaService;

    @Autowired
    public OperacionTransferenciaController(OperacionTransferenciaRepository operacionTransferenciaRepository, OperacionTransferenciaService operacionTransferenciaService) {
        this.operacionTransferenciaRepository = operacionTransferenciaRepository;
        this.operacionTransferenciaService = operacionTransferenciaService;
    }

    @GetMapping("/operacionesTransferencia")
    public String operacionesTransferencia(Model model) {
        model.addAttribute("operacionesTransferencia", operacionTransferenciaRepository.darOperacionesTransferencia());
        return "operacionesTransferencia";
}

    @GetMapping("/operacionesTransferencia/new")
    public String operacionTransferenciaForm(Model model) {
        model.addAttribute("operacionTransferencia", new OperacionTransferencia());
        return "operacionTransferenciaNuevo";
    }

    @PostMapping("/operacionesTransferencia/new/save")
    public String OperacionTransferenciaGuardar(@ModelAttribute OperacionTransferencia operacionTransferencia, @RequestParam("idPuntoAtencion") Integer idPuntoAtencion, @RequestParam("numCuentaOrigen") Integer numCuentaOrigen, @RequestParam("numCuentaDestino") Integer numCuentaDestino) throws InterruptedException{
        return operacionTransferenciaService.OperacionTransferenciaGuardar(operacionTransferencia, idPuntoAtencion, numCuentaOrigen, numCuentaDestino);
    }

}
