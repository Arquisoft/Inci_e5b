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
		// Convertimos el string JSON a un objeto Incidence mediante el puerto
		Incidence incidence;
		try {
			incidence = rIncidenceP.jsonStringToIncidence(jsonStringIncidence);
		} catch (BusinessException e) {
			System.out.println("Error al parsear la incidencia de JSON a Incidence");
			e.printStackTrace();
			return;
		}
		
		//Aplicamos el filtro y comprobamos si pasa o no y si tiene valores peligrosos o no
		incidence = dbManagement.getFilter().applyFilter(incidence);
		
		// Si pasa (es != null), la enviamos
		if(incidence!=null)
			receiveFilteredIncidence.receiveFilteredIncidence(incidence);
			
	}

}
