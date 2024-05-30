package uniandes.edu.co.proyecto.controller;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Empleado;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.EmpleadoRepository;


@SessionAttributes("numdoc")
@Controller
public class EmpleadoController {

    private final EmpleadoRepository empleadoRepository;
    private Empleado empleadoSeleccionado;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public EmpleadoController(EmpleadoRepository empleadoRepository, CuentaRepository cuentaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @RequestMapping("/filtro")
    public String index2() {
        return "filtro";
    }

    @RequestMapping("/extracto")
    public String index4() {
        return "extracto";
    }

    @RequestMapping("/gerenteDashBoard1")
    public String index3() {
        return "gerenteDashBoard2";
    }

    @RequestMapping(value="/empleados", method= RequestMethod.GET)
	public String viewLoginPage(ModelMap map) {
		return "empleados";
	}

    @GetMapping("/filtrarCuentas")
    public String cuentasFiltrar(Model model, @RequestParam("valorFiltro") String filtro, @RequestParam("filtro") String filtroTipo) {

        if ("tipo".equals(filtroTipo)) {
            model.addAttribute("Cuentas", cuentaRepository.darCuentasGerenteGeneralPorTipo(filtro));
            return "cuentasPorTipo";
        } 
        else if ("saldo".equals(filtroTipo)) {
            String[] saldos = filtro.split("-");
            if (saldos.length == 2) {
                try {
                Integer saldo1 = Integer.parseInt(saldos[0]);
                Integer saldo2 = Integer.parseInt(saldos[1]);

                
                model.addAttribute("Cuentas", cuentaRepository.darCuentasGerenteGeneralPorSaldos(saldo1, saldo2));
                return "cuentasPorTipo";
                } catch (NumberFormatException e) {
                return "error";
            }
        }
        } else if ("fecha".equals(filtroTipo)) {
            model.addAttribute("Cuentas", cuentaRepository.darCuentasGerenteGeneralPorFechaCreacion(filtro));
            return "cuentasPorTipo";
        } else if ("fecha_ultimo_movimiento".equals(filtroTipo)) {
            model.addAttribute("Cuentas", cuentaRepository.darCuentasGerenteGeneralPorFechaUltimaTransaccion(filtro));
            return "cuentasPorTipo";
        } else if ("cliente".equals(filtroTipo)) {
            model.addAttribute("Cuentas", cuentaRepository.darCuentasCliente(Integer.parseInt(filtro)));
            return "cuentasPorTipo";            
        }
        
        return "error";
    }
    

    @GetMapping("/gerenteOficinaCuentas")
    public String cuentasOficina(Model model) {
        model.addAttribute("Cuentas", cuentaRepository.darCuentas());
        return "cuentas";
    }

    @RequestMapping(value="/empleados", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String numDoc, @RequestParam String password){
        empleadoSeleccionado = empleadoRepository.darEmpleado(Integer.parseInt(numDoc));
        if (!empleadoSeleccionado.getPassword().equals(password)) {
            model.put("errorMessage", "Access Denied , Invalid Credentials");
            return "empleados";
        }
 
        model.put("numdoc", numDoc);
        model.put("password", password);

        if (this.empleadoSeleccionado != null) {
            String tipoEmpleado = this.empleadoSeleccionado.getCargo();
            if ("Cajero".equals(tipoEmpleado)) {
                return "redirect:/cajero";
            } else if ("Gerente General".equals(tipoEmpleado)) {
                return "redirect:/gerenteGeneral";
            } else if ("Gerente Oficina".equals(tipoEmpleado)) {
                return "redirect:/gerenteOficina";
            }
        }

        return "empleados";
    }

    @GetMapping("/empleadosLista")
    public String empleadosLista(Model model) {
        model.addAttribute("empleados", empleadoRepository.darEmpleados());
        return "empleadosLista";
    }

    @GetMapping("/cuentasOficina")
    public String cuentasOficinaLista(Model model, Integer idoficina) {
        model.addAttribute("cuentas", empleadoRepository.darCuentasGerenteOficina(idoficina));
        return "cuentasGerenteOficina";
    }


    @GetMapping("/empleados/new")
    public String empleadoForm(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleadoNuevo";
    }

    @PostMapping("/empleados/new/save")
    public String empleadoGuardar(@ModelAttribute Empleado empleado) {
        if (empleado.getPuntoAtencion() != null) {
            empleadoRepository.insertarEmpleado(empleado.getTipoDocumento(), empleado.getNumeroDocumento(), empleado.getNombre(), empleado.getNacionalidad(), empleado.getDireccionFisica(), empleado.getDireccionElectronica(), empleado.getTelefono(), empleado.getCiudad(), empleado.getDepartamento(), empleado.getCodigoPostal(), empleado.getPassword(), empleado.getCargo(), empleado.getPuntoAtencion().getId());
        } else {
            empleadoRepository.insertarEmpleado(empleado.getTipoDocumento(), empleado.getNumeroDocumento(), empleado.getNombre(), empleado.getNacionalidad(), empleado.getDireccionFisica(), empleado.getDireccionElectronica(), empleado.getTelefono(), empleado.getCiudad(), empleado.getDepartamento(), empleado.getCodigoPostal(), empleado.getPassword(), empleado.getCargo(), null);
        }
                return "redirect:/empleados";
    }

    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    
    

}



