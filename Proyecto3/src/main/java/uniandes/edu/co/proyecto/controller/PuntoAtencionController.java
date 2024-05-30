package uniandes.edu.co.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.PuntoAtencion;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepository;
import uniandes.edu.co.proyecto.services.OficinaService;

@Controller
public class PuntoAtencionController {

    private final PuntoAtencionRepository puntoAtencionRepository;
    private final OficinaService oficinaService;
    private final OficinaRepository oficinaRepository;

    @Autowired
    public PuntoAtencionController(PuntoAtencionRepository puntoAtencionRepository, OficinaRepository oficinaRepository, OficinaService oficinaService) {
        this.puntoAtencionRepository = puntoAtencionRepository;
        this.oficinaRepository = oficinaRepository; 
        this.oficinaService = oficinaService;
    }

    @GetMapping("/puntosAtencion")
    public String puntosAtencion(Model model) {
        List<PuntoAtencion> puntosAtencion = oficinaRepository.darPuntosAtencion();
        model.addAttribute("puntosAtencion", puntosAtencion);
        return "puntosAtencion";
}

    @GetMapping("/puntosAtencion/new")
    public String puntoAtencionForm(Model model) {
        model.addAttribute("puntoAtencion", new PuntoAtencion());
        return "puntoAtencionNuevo";
    }

    @PostMapping("/puntosAtencion/new/save")
    public String puntoAtencionGuardar(@ModelAttribute PuntoAtencion puntoAtencion) {
        if (oficinaRepository.findMaxIdPunto().isEmpty()){
            puntoAtencion.setid(1);
        }
        int max = oficinaRepository.findMaxIdPunto().get(0).getMaxIdPunto();
        if (max == 0){
            puntoAtencion.setid(1);
        } else 
        {
            puntoAtencion.setid(max+1);
        }
        oficinaService.crearPuntoDeAtencion(puntoAtencion.getIdOficina(), puntoAtencion);
        return "redirect:/puntosAtencion";
    }



    @GetMapping("/puntosAtencion/{id}/delete")
    public String puntoAtencionEliminar(@PathVariable("id") Integer id) {
        oficinaService.deletePuntoDeAtencionById(id);
        return "redirect:/puntosAtencion";
    }

}

