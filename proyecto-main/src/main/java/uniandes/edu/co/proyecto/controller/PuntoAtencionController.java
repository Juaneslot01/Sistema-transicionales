package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.PuntoAtencion;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepository;
import uniandes.edu.co.proyecto.service.Contador;

@Controller
public class PuntoAtencionController {

    private final PuntoAtencionRepository puntoAtencionRepository;
    private final OficinaRepository oficinaRepository;

    @Autowired
    public PuntoAtencionController(PuntoAtencionRepository puntoAtencionRepository, OficinaRepository oficinaRepository) {
        this.puntoAtencionRepository = puntoAtencionRepository;
        this.oficinaRepository = oficinaRepository; 
    }

    @GetMapping("/puntosAtencion")
    public String puntosAtencion(Model model) {
        model.addAttribute("puntosAtencion", puntoAtencionRepository.darPuntosAtencion());
        return "puntosAtencion";
}

    @GetMapping("/puntosAtencion/new")
    public String puntoAtencionForm(Model model) {
        model.addAttribute("puntoAtencion", new PuntoAtencion());
        return "puntoAtencionNuevo";
    }

    @PostMapping("/puntosAtencion/new/save")
    public String puntoAtencionGuardar(@ModelAttribute PuntoAtencion puntoAtencion, @RequestParam("idoficina") Integer idoficina) {
        uniandes.edu.co.proyecto.modelo.Oficina oficina = oficinaRepository.darOficina(idoficina);
        puntoAtencion.setOficina(oficina);
        puntoAtencionRepository.insertarPuntoAtencion(Contador.generarIdPuntoAtencion(), puntoAtencion.getTipo(), puntoAtencion.getUbicacion(), idoficina);
        return "redirect:/puntosAtencion";
    }

    @GetMapping("/puntosAtencion/{id}/delete")
    public String puntoAtencionEliminar(@PathVariable("id") Integer id) {
        puntoAtencionRepository.eliminarPuntoAtencion(id);
        return "redirect:/puntosAtencion";
    }

}

