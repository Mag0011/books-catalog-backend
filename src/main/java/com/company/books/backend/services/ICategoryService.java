package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.ModelResponseRest;

public interface ICategoryService {

	public ResponseEntity<ModelResponseRest<Category>> searchCategory();
	public ResponseEntity<ModelResponseRest<Category>> getCategoryById(Long id);
	public ResponseEntity<ModelResponseRest<Category>> createCategory(Category category);
	public ResponseEntity<ModelResponseRest<Category>> updateCategory(Category category,Long id);
	public ResponseEntity<ModelResponseRest<Category>> deleteCategory(Long id);
}
