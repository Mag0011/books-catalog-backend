package com.company.books.backend.response;

import java.util.List;

import com.company.books.backend.model.Category;

public class CategoryResponse {

	List<Category> category;

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
}
