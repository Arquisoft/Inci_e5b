package uo.asw.dbManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.repositories.IncidencesRepository;

@Component
public class GetIncidencesSentByAgentImpl implements GetIncidencesSentByAgent {

	@Autowired
	private IncidencesRepository incidencesRepository;
	
	@Override
	public List<Incidence> getIncidencesSentByAgent(String agentIdentifier) {
		return incidencesRepository.getIncidencesSentByAgent(agentIdentifier);
	}

}
