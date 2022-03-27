package com.company.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/books/{id}")
	public ResponseEntity<ModelResponseRest<Book>> getBookById(@PathVariable(name = "id") Long id){
		return bookService.getBookById(id);
	}
	
	@PostMapping("/books")
	public ResponseEntity<ModelResponseRest<Book>> createBook(@RequestBody Book book){
		return bookService.createBook(book);
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<ModelResponseRest<Book>> updateBook(@RequestBody Book book, @PathVariable(name="id") Long id){
		return bookService.updateBook(book,id);
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<ModelResponseRest<Book>> deleteBook(@PathVariable(name="id") Long id){
		return bookService.deleteBook(id);
	}
	
}
