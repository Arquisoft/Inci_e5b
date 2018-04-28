package uo.asw.inciDashboard_e5b.dbManagement.repositories;

import org.springframework.data.repository.CrudRepository;

import uo.asw.inciDashboard_e5b.dbManagement.model.Operator;

public interface OperatorsRepository extends CrudRepository<Operator, Long>{
	Operator findByIdentifier(String identifier);
}
