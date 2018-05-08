package uo.asw.dbManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uo.asw.dbManagement.model.Category;
import uo.asw.dbManagement.repositories.CategoriesRepository;

@Component
public class SaveCategoryImpl implements SaveCategory {

	@Autowired
	private CategoriesRepository categoriesRepository;

	@Override
    public void saveCategory(Category category) {
    		List<Category> categories = new ArrayList<Category>();
    		categoriesRepository.findAll().forEach(categories::add);
    	
    		if(! categories.contains(category) )
    			categoriesRepository.save(category);
    		
    }

}
