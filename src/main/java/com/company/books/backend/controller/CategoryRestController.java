package com.company.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.ModelResponseRest;
import com.company.books.backend.services.ICategoryService;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<ModelResponseRest<Category>> getCategory() {
		ResponseEntity<ModelResponseRest<Category>> response = categoryService.searchCategory();
		return response;
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<ModelResponseRest<Category>> getCategoryById(@PathVariable(name = "id") Long id) {
		ResponseEntity<ModelResponseRest<Category>> response = categoryService.getCategoryById(id);
		return response;
	}
	
	@PostMapping("/categories")
	public ResponseEntity<ModelResponseRest<Category>> createCategory(@RequestBody Category category) {
		ResponseEntity<ModelResponseRest<Category>> response = categoryService.createCategory(category);
		return response;
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<ModelResponseRest<Category>> updateCategory(@RequestBody Category category, @PathVariable Long id) {
		ResponseEntity<ModelResponseRest<Category>> response = categoryService.updateCategory(category, id);
		return response;
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<ModelResponseRest<Category>> deleteCategory(@PathVariable Long id) {
		ResponseEntity<ModelResponseRest<Category>> response = categoryService.deleteCategory(id);
		return response;
	}
	
}
