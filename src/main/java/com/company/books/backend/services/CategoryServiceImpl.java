package com.company.books.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.dao.ICategoryDao;
import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {

	public static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchCategory() {
		log.info("Inicio de búsqueda de ctaegoria");
		CategoryResponseRest response =  new CategoryResponseRest();
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Response", "200", "Successful response");
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setMetadata("Response", "-1", "Error when category was consulted");
			log.error("Error when category was consulted", ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
			
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // 200
	}
	
}
