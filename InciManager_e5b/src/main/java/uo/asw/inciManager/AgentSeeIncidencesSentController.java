package uo.asw.inciManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgentSeeIncidencesSentController implements AgentSeeIncidencesSent {

	@Autowired
	private IncidenceService incidenceService;
	
	@Override
	@RequestMapping(value = "/incidence/sent")
	public String getIncidencesSent(Model model) {
		// Enviamos a la vista las incidencias enviadas por ese agente
		// que hayan sido almacenadas en la base de datos
		model.addAttribute("incidences",	incidenceService.getIncidencesSentByAgent("31668313G", "1234", "Person"));
		
		return "incidence/sent";
	}

}
