package com.company.books.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> getCategoryById(Long id) {
		log.info("Looking category by id");
		CategoryResponseRest response =  new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Optional<Category> category = categoryDao.findById(id);
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Response", "200", "Successful response");
			}else {
				log.error("Category was not found");
				response.setMetadata("Response failed", "-1", "Category was not found");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND); // 404
			}
		}catch(Exception ex) {
			log.error("Internal Server Error");
			response.setMetadata("Response failed", "-1", "Category was not found");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> createCategory(Category category) {
		log.info("Creating a category");
		CategoryResponseRest response =  new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Category categorySaved =  categoryDao.save(category);
			if(categorySaved != null) {
				log.info("Registry added");
				list.add(categorySaved);
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Response", "200", "Succesful creation");
			}else {
				log.error("Entry couldn´t be saved");
				response.setMetadata("Response", "-1", "Failed in creation");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST); // 400
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setMetadata("Response", "-1", "Error in category creation");
			log.error("Error when category was consulted", ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
			
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id) {
		log.info("Updating a category");
		CategoryResponseRest response =  new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Optional<Category> categorySearched =  categoryDao.findById(id);
			if(categorySearched.isPresent()) {
				categorySearched.get().setName(category.getName());
				categorySearched.get().setDescription(category.getDescription());
				Category categoryUpdated = categoryDao.save(categorySearched.get());
				if(categoryUpdated != null) {
					log.info("Registry updated");
					list.add(categoryUpdated);
					response.getCategoryResponse().setCategory(list);
					response.setMetadata("Response OK", "200", "Succesful update");
				}else {
					log.error("Entry couldn´t be updated");
					response.setMetadata("Response", "-1", "Failed in creation");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST); // 200
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setMetadata("Response", "-1", "Error in category creation");
			log.error("Error when category was updated", ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
			
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // 200
	}

	@Override
	public ResponseEntity<CategoryResponseRest> deleteCategory(Long id) {
		log.info("Deleting a category");
		CategoryResponseRest response =  new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			categoryDao.deleteById(id);
			response.setMetadata("Response OK", "200", "Succesful elimination");
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setMetadata("Response", "-1", "Error in category creation");
			log.error("Error when category was consulted", ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
			
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // 200
	}
	
}
