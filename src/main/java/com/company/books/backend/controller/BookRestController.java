package com.company.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.ModelResponseRest;
import com.company.books.backend.services.IBookService;

@RestController
@RequestMapping("/v1")
public class BookRestController {

	@Autowired
	private IBookService bookService;
	
	@GetMapping("/books")
	public ResponseEntity<ModelResponseRest<Book>> getAllBooks(){
		return bookService.getAllBooks();
	}
	
}
