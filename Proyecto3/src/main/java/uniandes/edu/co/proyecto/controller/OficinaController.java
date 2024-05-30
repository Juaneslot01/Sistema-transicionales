package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.services.OficinaService;

@Controller
public class OficinaController {

    private final OficinaRepository oficinaRepository;
    private final OficinaService oficinaService;

    @Autowired
    public OficinaController(OficinaRepository oficinaRepository, OficinaService oficinaService) {
        this.oficinaRepository = oficinaRepository;
        this.oficinaService = oficinaService;
    }

    @GetMapping("/oficinas")
    public String oficinas(Model model) {
        model.addAttribute("oficinas", oficinaRepository.darOficinas());
        return "oficinas";
}

    @GetMapping("/oficinas/new")
    public String oficinaForm(Model model) {
        model.addAttribute("oficina", new Oficina());
        return "oficinaNuevo";
    }

    @PostMapping("/oficinas/new/save")
    public String oficinaGuardar(@ModelAttribute Oficina oficina) {
        if (oficinaRepository.findTop1ByOrderByIdDesc().isEmpty()) {
            oficina.setid(1);
        } else oficina.setid(oficinaRepository.findTop1ByOrderByIdDesc().iterator().next().getid() + 1);
        oficinaRepository.save(oficina);
        return "redirect:/oficinas";
    }

    
    



}
