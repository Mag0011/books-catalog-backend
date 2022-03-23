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
import com.company.books.backend.response.ModelResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {

	public static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ModelResponseRest<Category>> searchCategory() {
		log.info("Starting category look up");
		ModelResponseRest<Category> response =  new ModelResponseRest<Category>();
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();
			response.getModelResponse().setModel(category);
			response.setMetadata("Response", "200", "Successful response");
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setMetadata("Response", "-1", "Error when category was consulted");
			log.error("Error when category was consulted", ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
			
		}
		return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.OK); // 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ModelResponseRest<Category>> getCategoryById(Long id) {
		log.info("Looking category by id");
		ModelResponseRest<Category> response =  new ModelResponseRest<Category>();
		List<Category> list = new ArrayList<>();
		try {
			Optional<Category> category = categoryDao.findById(id);
			if(category.isPresent()) {
				list.add(category.get());
				response.getModelResponse().setModel(list);
				response.setMetadata("Response", "200", "Successful response");
			}else {
				log.error("Category was not found");
				response.setMetadata("Response failed", "-1", "Category was not found");
				return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.NOT_FOUND); // 404
			}
		}catch(Exception ex) {
			log.error("Internal Server Error");
			response.setMetadata("Response failed", "-1", "Category was not found");
			return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
		return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.OK); // 200
	}

	@Override
	@Transactional
	public ResponseEntity<ModelResponseRest<Category>> createCategory(Category category) {
		log.info("Creating a category");
		ModelResponseRest<Category> response =  new ModelResponseRest<Category>();
		List<Category> list = new ArrayList<>();
		try {
			Category categorySaved =  categoryDao.save(category);
			if(categorySaved != null) {
				log.info("Registry added");
				list.add(categorySaved);
				response.getModelResponse().setModel(list);
				response.setMetadata("Response", "200", "Succesful creation");
			}else {
				log.error("Entry couldn´t be saved");
				response.setMetadata("Response", "-1", "Failed in creation");
				return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.BAD_REQUEST); // 400
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setMetadata("Response", "-1", "Error in category creation");
			log.error("Error when category was consulted", ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
			
		}
		return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.OK); // 200
	}

	@Override
	@Transactional
	public ResponseEntity<ModelResponseRest<Category>> updateCategory(Category category, Long id) {
		log.info("Updating a category");
		ModelResponseRest<Category> response =  new ModelResponseRest<Category>();
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
					response.getModelResponse().setModel(list);
					response.setMetadata("Response OK", "200", "Succesful update");
				}else {
					log.error("Entry couldn´t be updated");
					response.setMetadata("Response", "-1", "Failed in creation");
					return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.BAD_REQUEST); // 200
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setMetadata("Response", "-1", "Error in category creation");
			log.error("Error when category was updated", ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
			
		}
		return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.OK); // 200
	}

	@Override
	@Transactional
	public ResponseEntity<ModelResponseRest<Category>> deleteCategory(Long id) {
		log.info("Deleting a category");
		ModelResponseRest<Category> response =  new ModelResponseRest<Category>();
		try {
			categoryDao.deleteById(id);
			response.setMetadata("Response OK", "200", "Succesful elimination");
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setMetadata("Response", "-1", "Error in category creation");
			log.error("Error when category was consulted", ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
			
		}
		return new ResponseEntity<ModelResponseRest<Category>>(response, HttpStatus.OK); // 200
	}
	
}
