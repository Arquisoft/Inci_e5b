package uo.asw.dbManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Category;
import uo.asw.dbManagement.model.Incidence;

public interface CategoriesRepository extends CrudRepository<Category, Long>{
	
}
