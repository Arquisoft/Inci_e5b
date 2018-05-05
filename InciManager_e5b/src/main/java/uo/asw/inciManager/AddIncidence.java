package uo.asw.inciManager;

import org.springframework.ui.Model;

public interface AddIncidence {

	String addIncidence(Model model);

	String addIncidence(String login, String password, String kind, String name, String description, String location,
			String tags, String properties);

}
