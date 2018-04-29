package uo.asw.inciDashboard.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uo.asw.dbManagement.DBManagementFacade;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.inciDashboard.currentIncidences.ReceiveFilteredIncidence;
import uo.asw.util.exception.BusinessException;

@Component
public class ReceiveIncidenceImpl implements ReceiveIncidence {

	@Autowired
	private RIncidenceP rIncidenceP; 
	
	@Autowired
	private DBManagementFacade dbManagement; 
	
	@Autowired
	private ReceiveFilteredIncidence receiveFilteredIncidence;
	
	@Override
	public void receiveIncidence(String jsonStringIncidence) {
		Incidence incidence;
		try {
			incidence = rIncidenceP.jsonStringToIncidence(jsonStringIncidence);
		} catch (BusinessException e) {
			return;
		}
		
		//Aplicamos el filtro y comprobamos si pasa o no y si tiene valores peligrosos o no
		incidence = dbManagement.getFilter().applyFilter(incidence);
		
		if(incidence!=null)
			receiveFilteredIncidence.receiveFilteredIncidence(incidence);
			
	}

}
