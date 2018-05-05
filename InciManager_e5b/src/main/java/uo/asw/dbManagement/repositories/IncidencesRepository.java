package uo.asw.dbManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Incidence;

public interface IncidencesRepository extends CrudRepository<Incidence, Long>{

	@Query("SELECT i FROM Incidence i WHERE i.agent.identifier = ?1")
	List<Incidence> getIncidencesSentByAgent(String agentIdentifier);
	
}
