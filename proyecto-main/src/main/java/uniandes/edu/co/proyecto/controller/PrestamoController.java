package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Prestamo;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.repositorio.PrestamoRepository;
import uniandes.edu.co.proyecto.service.Contador;

@Controller
public class PrestamoController {
    private final PrestamoRepository prestamoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public PrestamoController(PrestamoRepository prestamoRepository, ClienteRepository clienteRepository) {
        this.prestamoRepository = prestamoRepository;
        this.clienteRepository = clienteRepository; 
    }

    @GetMapping("/prestamos")
    public String prestamos(Model model) {
        model.addAttribute("prestamos", prestamoRepository.darPrestamos());
        return "prestamos";
}

    @GetMapping("/prestamos/new")
    public String prestamoForm(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        return "prestamoNuevo";
    }

    @PostMapping("/prestamos/new/save")
    public String prestamoGuardar(@ModelAttribute Prestamo prestamos, @RequestParam("numdoccliente") Integer numDocCliente) {
        uniandes.edu.co.proyecto.modelo.Cliente cliente = clienteRepository.darCliente(numDocCliente);
        prestamos.setCliente(cliente);
        prestamoRepository.insertarPrestamo(Contador.generarNumPrestamo(), prestamos.getTipoPrestamo(), prestamos.getMonto(), prestamos.getIntereses(), prestamos.getEstado(), prestamos.getNumCuotas(), prestamos.getDiaPagoProximaCuota(), numDocCliente);
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/{numPrestamo}/edit")
    public String prestamoEditarForm(@PathVariable("numPrestamo") Integer numPrestamo, Model model) {
        Prestamo prestamo = prestamoRepository.darPrestamo(numPrestamo);
        if (prestamo != null) {
            model.addAttribute("prestamo", prestamo);
            return "prestamoEditar";
        } else {
            return "redirect:/prestamos";
        }
    }

    @PostMapping("/prestamos/{numPrestamo}/edit/save")
    public String prestamoEditarGuardar(@PathVariable("numPrestamo") Integer numPrestamo, @ModelAttribute Prestamo prestamo) {
    prestamoRepository.actualizarEstadoPrestamo(numPrestamo, prestamo.getEstado());
    
    return "redirect:/prestamos";
}

    

}
