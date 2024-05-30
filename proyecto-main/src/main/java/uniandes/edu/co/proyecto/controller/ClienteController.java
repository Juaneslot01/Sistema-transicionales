package uniandes.edu.co.proyecto.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;

@SessionAttributes("numdoc")
@Controller
public class ClienteController {
    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;
    private Cliente currentCliente;

    @Autowired
    public ClienteController(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/consultarCliente")
    public String consultarClienteCompleto(Model model, @RequestParam("numeroDocumento") String idCliente) {
        System.out.println("ID Cliente: " + Integer.parseInt(idCliente));
        model.addAttribute("resultados", clienteRepository.consultarClienteCompleto(Integer.parseInt(idCliente)));
        return "clientes2";
    }

    @RequestMapping(value="/cliente", method= RequestMethod.GET)
	public String viewLoginPage(ModelMap map) {
		return "cliente";
	}

    @RequestMapping(value="/cliente", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String numDoc, @RequestParam String password){
        currentCliente = clienteRepository.darCliente(Integer.parseInt(numDoc));
        if (!currentCliente.getPassword().equals(password)) {
            model.put("errorMessage", "Access Denied , Invalid Credentials");
            return "redirect:/cuentasCliente";
        }
 
        model.put("numdoc", numDoc);
        model.put("password", password);
 
        return "redirect:/cuentasCliente";
    }

    @RequestMapping("/clienteDashboard")
    public String index1() {
        return "clienteDashboard";
    }

    
    @RequestMapping("/cliente")
    public String index() {
        return "cliente";
    }
    
    @GetMapping("/clientes")
    public String clientes(Model model) {
        model.addAttribute("clientes", clienteRepository.darClientes());
        return "clientes";
    }

    @GetMapping("/buscar-cliente")
    public String cliente1(Model model, @RequestParam("numeroDocumento") int numeroDocumento) {
        Cliente cliente = clienteRepository.darCliente(numeroDocumento);
        model.addAttribute("clientes", Collections.singletonList(cliente));
        return "gerenteDashBoard1";
    }
    

    @GetMapping("/cuentasCliente")
    public String cuentasCliente(Model model) {
        model.addAttribute("cuentasCliente", cuentaRepository.darCuentasCliente(currentCliente.getNumeroDocumento()));
        return "clienteDashboard";
    }

    @GetMapping("/clientes/new")
    public String clienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clienteNuevo";
    }

    @PostMapping("/clientes/new/save")
    public String clienteGuardar(@ModelAttribute Cliente cliente) {
        clienteRepository.insertarCliente(cliente.getTipoDocumento(), cliente.getNumeroDocumento(), cliente.getNombre(), cliente.getNacionalidad(), cliente.getDireccionFisica(), cliente.getDireccionElectronica(), cliente.getTelefono(), cliente.getCiudad(), cliente.getDepartamento(), cliente.getCodigoPostal(), cliente.getPassword());
        return "redirect:/clientes";
    }

}
