package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.ModelResponseRest;

public interface IBookService {

	public ResponseEntity<ModelResponseRest<Book>> getAllBooks();
	
}
