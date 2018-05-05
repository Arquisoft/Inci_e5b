package uo.asw.inciManager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.util.CSVKindsReader;

@Controller
public class AddIncidenceController implements AddIncidence{

	@Autowired
	private IncidenceService incidenceService;
	
	@Override
	@RequestMapping(value = "/incidence/add")
	public String addIncidence(Model model) {
		// Enviamos a la vista los tipos de agente, sacados del fichero csv
		model.addAttribute("kinds", 	CSVKindsReader.getKinds());
		
		return "incidence/add";
	}
	
	/**
	 * Recoge los datos del formulario web y crea la incidencia
	 */
	@Override
	@RequestMapping(value = "/incidence/add", method = RequestMethod.POST)
	public String addIncidence(@RequestParam String login,@RequestParam String password,@RequestParam String kind, 
			@RequestParam String name, @RequestParam String description, @RequestParam String location, 
			@RequestParam String tags, @RequestParam String properties) {
		
		// Se crea la incidencia con los campos del formulario web.
		Incidence incidence = incidenceService.createIncidence(name, description, location, tags, properties);
		
		// Si existe el agente, se devuelve a la vista inicial. Si no, a la vista de error.
		if (incidenceService.manageIncidence(login, password, kind, incidence))
			return "redirect:/?enviadaCorrectamente=true";
		else
			return "redirect:/managerError?mensajeError=Error al mandar la incidencia. Agente no valido.";
		
	}
	
	/**
	 * Recoge los datos de la petición JSON y crea la incidencia
	 * @param payload
	 * @return
	 */
	@Override
	@RequestMapping(value = "/api/incidence", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<WSResponse> addIncidenceApi(@RequestBody Map<String, Object> payload) {
		
		//Sacamos los parametros de la peticion
		String login = (String) payload.get("login");
		String password = (String) payload.get("password");
		String kind = (String) payload.get("kind");
		String name = (String) payload.get("name");
		String description = (String) payload.get("description");
		String location = (String) payload.get("location");
		String tags = (String) payload.get("tags");
		String properties = (String) payload.get("properties");
		
		// Los campos login, password, kind, name y description son obligatorios
		if(login == null || password == null || kind == null || name == null || description == null) {
			return new ResponseEntity<WSResponse>(
					new WSResponse("Los campos login, password, kind, name y description son obligatorios."),
					HttpStatus.BAD_REQUEST);
		}
		
		// Se crea la incidencia con los campos del formulario web.
		Incidence incidence = incidenceService.createIncidence(name, description, location, tags, properties);
		
		// Si existe el agente, se devuelve un codigo 200. Si no, un codigo 404.
		if (incidenceService.manageIncidence(login, password, kind, incidence))
			return new ResponseEntity<WSResponse>( new WSResponse("Incidencia enviada correctamente"), HttpStatus.OK );
		else
			return new ResponseEntity<WSResponse>( new WSResponse("Agente no encontrado"), HttpStatus.NOT_FOUND );		
	}
	
}
