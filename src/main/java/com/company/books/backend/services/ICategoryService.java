package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;

public interface ICategoryService {

	public ResponseEntity<CategoryResponseRest> searchCategory();
	public ResponseEntity<CategoryResponseRest> getCategoryById(Long id);
	public ResponseEntity<CategoryResponseRest> createCategory(Category category);
}
