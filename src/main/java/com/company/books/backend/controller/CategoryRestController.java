package com.company.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.services.ICategoryService;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/categories")
	public CategoryResponseRest getCategory() {
		CategoryResponseRest response = categoryService.searchCategory();
		return response;
	}
	
}
