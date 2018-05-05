package uo.asw.dbManagement;

import java.util.List;

import uo.asw.dbManagement.model.Incidence;

public interface GetIncidencesSentByAgent {
	
	List<Incidence> getIncidencesSentByAgent(String agentIdentifier);

}
