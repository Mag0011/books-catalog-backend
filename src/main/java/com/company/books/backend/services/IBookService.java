package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.ModelResponseRest;

public interface IBookService {

	public ResponseEntity<ModelResponseRest<Book>> getAllBooks();
	public ResponseEntity<ModelResponseRest<Book>> getBookById(Long id);
	public ResponseEntity<ModelResponseRest<Book>> createBook(Book book);
	public ResponseEntity<ModelResponseRest<Book>> updateBook(Book book, Long id);
	public ResponseEntity<ModelResponseRest<Book>> deleteBook(Long id);
	
}
