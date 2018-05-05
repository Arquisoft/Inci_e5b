package uo.asw.inciManager;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface AddIncidence {

	String addIncidence(Model model);

	String addIncidence(String login, String password, String kind, String name, String description, String location,
			String tags, String properties);
	
	ResponseEntity<WSResponse> addIncidenceApi(Map<String, Object> payload);

}
