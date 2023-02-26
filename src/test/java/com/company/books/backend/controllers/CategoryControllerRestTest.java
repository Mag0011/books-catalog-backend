package com.company.books.backend.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.books.backend.controller.CategoryRestController;
import com.company.books.backend.model.Category;
import com.company.books.backend.response.ModelResponseRest;
import com.company.books.backend.services.ICategoryService;

public class CategoryControllerRestTest {

	@Mock
	private ICategoryService categoryService;
	
	@InjectMocks
	CategoryRestController categoryConroller;
	
	@BeforeEach
	public void init() {
		 MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void createCategory() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		
		Category categoryRequest = new Category("Classics","Literature");
		
		// constructing object for response
		Category categoryResponse = new Category(1L,"Classics","Literature");
		List<Category> listCategory = new ArrayList<>();
		listCategory.add(categoryResponse);
		ModelResponseRest<Category> responseService = new ModelResponseRest<Category>();
		responseService.getModelResponse().setModel(listCategory);
		
		
		when(categoryService.createCategory(any(Category.class))).thenReturn(new ResponseEntity<ModelResponseRest<Category>>(responseService,HttpStatus.OK));
		
		ResponseEntity<ModelResponseRest<Category>> response = categoryConroller.createCategory(categoryRequest);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertTrue(responseService.getModelResponse().getModel().size() > 0);
		assertTrue(responseService.getModelResponse().getModel().get(0).getId() != null);
		
		verify(categoryService, times(1)).createCategory(categoryRequest);
	}
	
}
