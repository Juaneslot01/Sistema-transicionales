package uniandes.edu.co.proyecto.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;


@Controller
public class GerenteOficinaController {
    @RequestMapping("/gerenteOficina")
    public String index() {
        return "gerenteOficina";
    }

    @RequestMapping("/cuentasGerenteOficina")
    public String index1() {
        return "cuentasGerenteOficina";
    }
}
