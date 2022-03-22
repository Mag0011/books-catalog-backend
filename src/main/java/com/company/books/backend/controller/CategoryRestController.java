package com.company.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.services.ICategoryService;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> getCategory() {
		ResponseEntity<CategoryResponseRest> response = categoryService.searchCategory();
		return response;
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> getCategoryById(@PathVariable(name = "id") Long id) {
		ResponseEntity<CategoryResponseRest> response = categoryService.getCategoryById(id);
		return response;
	}
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> createCategory(@RequestBody Category category) {
		ResponseEntity<CategoryResponseRest> response = categoryService.createCategory(category);
		return response;
	}
	
	@PutMapping("/categories")
	public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category, @PathVariable Long id) {
		ResponseEntity<CategoryResponseRest> response = categoryService.updateCategory(category, id);
		return response;
	}
	
}
