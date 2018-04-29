
package uo.asw.inciDashboard.currentIncidences;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import uo.asw.dbManagement.model.Incidence;

@Controller
public class CurrentIncidencesController implements GetCurrentIncidences {
	
	@Autowired
	private ReceiveFilteredIncidenceImpl receiveService;
	

	/* TODO
	 * Ofrece una monitorización continua de la evolución de los valores de las propiedades más representativas de los sensores, 
	 * así como de las incidencias que estén siendo generadas por las personas o entidades. 
	 * También se ofrecerá la posibilidad de visualizar las incidencias geolocalizadas en un mapa, así como los valores actuales y los estados. 
	 */
	
	@RequestMapping("/incidences/currentIncidences")
	public String getCurrentIncidences(Model model) {
		List<Incidence> incidencias = receiveService.getListaincidencias();
		model.addAttribute("incidenceList", incidencias); 
		//Page<Incidence> incidences =  (Page<Incidence>) receiveService.getListaincidencias();
		return "incidences/currentIncidences";
	}


	@Override
	public String getCurrentIncidences() {
		// TODO Auto-generated method stub
		return null;
	}
}

