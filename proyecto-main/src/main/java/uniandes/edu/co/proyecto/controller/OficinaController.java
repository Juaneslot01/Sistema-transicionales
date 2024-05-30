package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.repositorio.EmpleadoRepository;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.service.Contador;

@Controller
public class OficinaController {

    private final OficinaRepository oficinaRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public OficinaController(OficinaRepository oficinaRepository, EmpleadoRepository empleadoRepository) {
        this.oficinaRepository = oficinaRepository;
        this.empleadoRepository = empleadoRepository;
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
    public String oficinaGuardar(@ModelAttribute Oficina oficina, @RequestParam("numdocgerente") Integer numDocGerente) {
        uniandes.edu.co.proyecto.modelo.Empleado empleado = empleadoRepository.darEmpleado(numDocGerente);
        oficina.setEmpleado(empleado);
        oficinaRepository.insertarOficina(Contador.generarIdOficina(), oficina.getNombre(), oficina.getDireccion(), oficina.getNumPuntosAtencion(), numDocGerente);
        return "redirect:/oficinas";
    }


}
