package uo.asw.inciDashboard_e5b.inciDashboard.storedIncidences;

import org.springframework.ui.Model;

public interface ShowIncidencesOfCategory {

	public String showIncidencesOfCategoryGet(Model model, Long category_id);
	
}
