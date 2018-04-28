package uo.asw.inciDashboard_e5b.dbManagement.repositories;

import org.springframework.data.repository.CrudRepository;

import uo.asw.inciDashboard_e5b.dbManagement.model.Filter;

public interface FilterRepository extends CrudRepository<Filter, Long>{
	
	Filter findById(Long id);
}
