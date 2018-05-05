package uo.asw.inciManager;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uo.asw.util.CSVKindsReader;

@Controller
public class AgentSeeIncidencesSentController implements AgentSeeIncidencesSent {

	@Autowired
	private IncidenceService incidenceService;
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping(value = "/incidence/sent/agentConfirm")
	public String incidencesSentAgentConfirm(Model model) {
		// Enviamos a la vista los tipos de agente, sacados del fichero csv
		model.addAttribute("kinds", 	CSVKindsReader.getKinds());
		
		return "incidence/sentAgentConfirm";
	}
	
	/**
	 * Recoge los datos del formulario web para comprobar si dicho agente es valido.
	 * En caso de serlo, le redirige a su lista de incidencias enviadas. 
	 */
	@RequestMapping(value = "/incidence/sent/agentConfirm", method = RequestMethod.POST)
	public String incidencesSentAgentConfirmPost(@RequestParam String login, @RequestParam String password,
			@RequestParam String kind) {
		
		if(incidenceService.loginCorrecto(login, password, kind)) {
			// Si existe el agente, guardamos sus datos en sesion
			httpSession.setAttribute("login", login);
			httpSession.setAttribute("password", password);
			httpSession.setAttribute("kind", kind);
			
			return "redirect:/incidence/sent";
		}
		else {
			// Si no existe el agente, borramos sus datos de sesion
			httpSession.setAttribute("login", null);
			httpSession.setAttribute("password", null);
			httpSession.setAttribute("kind", null);
			
			return "redirect:/managerError?mensajeError=Agente no valido.";
		}
		
	}
	
	@Override
	@RequestMapping(value = "/incidence/sent")
	public String getIncidencesSent(Model model) {
		String login = (String) httpSession.getAttribute("login");
		String password = (String) httpSession.getAttribute("password");
		String kind = (String) httpSession.getAttribute("kind");
		
		if(login == null || password == null || kind == null)
			return "redirect:/managerError?mensajeError=Agente no valido.";
		
		// Enviamos a la vista las incidencias enviadas por ese agente
		// que hayan sido almacenadas en la base de datos
		model.addAttribute("incidences",	incidenceService.getIncidencesSentByAgent(login, password, kind));
		
		return "incidence/sent";
	}

}
