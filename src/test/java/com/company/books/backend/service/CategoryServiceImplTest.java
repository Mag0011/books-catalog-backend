package com.company.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.controller.CategoryRestController;
import com.company.books.backend.dao.CategoryDao;
import com.company.books.backend.model.Category;
import com.company.books.backend.response.ModelResponseRest;
import com.company.books.backend.services.CategoryServiceImpl;

public class CategoryServiceImplTest {

	@InjectMocks
	CategoryServiceImpl categoryService;
	
	@Mock
	CategoryDao categoryDao;
	
	List<Category> categoriesList = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.loadCategories();
	}
	
	@Test
	public void seachCategoryTest() {
		when(categoryDao.findAll()).thenReturn(categoriesList);
		ResponseEntity<ModelResponseRest<Category>> response =  categoryService.searchCategory();
		assertEquals(4, response.getBody().getModelResponse().getModel().size());
		
		verify(categoryDao, times(1)).findAll();
	}

	public void loadCategories() {
		Category category1 =  new Category(1000L,"Science","Sci-Fi Books");
		Category category2 =  new Category(1001L,"Psychology","Learning book");
		Category category3 =  new Category(1002L,"Comics","Super Comic Heroes");
		Category category4 =  new Category(1003L,"Mystery","Books of mysteries");
		categoriesList.add(category1);
		categoriesList.add(category2);
		categoriesList.add(category3);
		categoriesList.add(category4);
	}
	
}
