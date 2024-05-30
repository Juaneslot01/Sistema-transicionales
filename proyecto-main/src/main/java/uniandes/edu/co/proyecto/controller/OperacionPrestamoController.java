package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.OperacionPrestamo;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionPrestamoRepository;
import uniandes.edu.co.proyecto.repositorio.PrestamoRepository;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepository;
import uniandes.edu.co.proyecto.service.Contador;

@Controller
public class OperacionPrestamoController {

    private final OperacionPrestamoRepository operacionPrestamoRepository;
    private final PrestamoRepository prestamoRepository;
    private final PuntoAtencionRepository puntoAtencionRepository;

    @Autowired
    public OperacionPrestamoController(OperacionPrestamoRepository operacionPrestamoRepository, PuntoAtencionRepository puntoAtencionRepository, PrestamoRepository prestamoRepository) {
        this.operacionPrestamoRepository = operacionPrestamoRepository;
        this.prestamoRepository = prestamoRepository;
        this.puntoAtencionRepository = puntoAtencionRepository;
    }

    @GetMapping("/operacionesPrestamo")
    public String operacionesPrestamo(Model model) {
        model.addAttribute("operacionesPrestamo", operacionPrestamoRepository.darOperacionesPrestamo());
        return "operacionesPrestamo";
}

    @GetMapping("/operacionesPrestamo/new")
    public String operacionPrestamoForm(Model model) {
        model.addAttribute("operacionPrestamo", new OperacionPrestamo());
        return "operacionPrestamoNuevo";
    }

    @PostMapping("/operacionesPrestamo/new/save")
    public String OperacionPrestamoGuardar(@ModelAttribute OperacionPrestamo operacionPrestamo, @RequestParam("idPuntoAtencion") Integer idPuntoAtencion, @RequestParam("numPrestamo") Integer numPrestamo) {
        uniandes.edu.co.proyecto.modelo.Prestamo prestamo = prestamoRepository.darPrestamo(numPrestamo);
        uniandes.edu.co.proyecto.modelo.PuntoAtencion puntoAtencion = puntoAtencionRepository.darPuntoAtencion(idPuntoAtencion);
        operacionPrestamo.setPuntoAtencion(puntoAtencion);
        operacionPrestamo.setPrestamo(prestamo);
        if (prestamo.getMonto() > operacionPrestamo.getValor() && prestamo.getEstado().equals("Activa")) {
            prestamoRepository.actualizarMontoPrestamo(numPrestamo, prestamo.getMonto()-operacionPrestamo.getValor());
            operacionPrestamoRepository.insertarOperacionPrestamo(Contador.generarIdOperacionPrestamo(), operacionPrestamo.getTipoOperacion(), operacionPrestamo.getValor(), operacionPrestamo.getFecha(), idPuntoAtencion, numPrestamo);
        }
        else throw new IllegalArgumentException("El monto de la operacion no puede ser mayor al monto del prestamo o el préstamo no está activo");
        return "redirect:/operacionesPrestamo";
    }

}