package com.company.books.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.dao.IBookDao;
import com.company.books.backend.model.Book;
import com.company.books.backend.response.ModelResponseRest;
import org.slf4j.Logger;

@Service
public class BookServiceImpl implements IBookService{
	
	public static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private IBookDao bookDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ModelResponseRest<Book>> getAllBooks() {
		
		ModelResponseRest<Book> response =  new ModelResponseRest<>();
		List<Book> listBooks = new ArrayList<Book>();
		try {
			log.info("[1100] Books lookup: Started");
			listBooks = (List<Book>) bookDao.findAll();
			response.getModelResponse().setModel(listBooks);
			response.setMetadata("Response", "200", "Successful operation");
		}catch(Exception ex) {
			log.error("[1100] Error when books were consulted", ex.getMessage());
			response.setMetadata("Response", "-1", "Operation failed");
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("[1100] Books lookup: Finisheds");
		return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ModelResponseRest<Book>> getBookById(Long id) {
		ModelResponseRest<Book> response =  new ModelResponseRest<>();
		List<Book> listBook = new ArrayList<Book>();
		log.info("[1100] Books lookup: Started");
		try {
			Optional<Book> book =  bookDao.findById(id);
			if(book.isPresent()) {
				listBook.add(book.get());
				response.getModelResponse().setModel(listBook);
				response.setMetadata("Response", "200", "Successful operation");
			}else {
				response.setMetadata("Response", "00", "Successful operation");
				return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.BAD_GATEWAY);
			}
		}catch(Exception ex) {
			log.error("[1100] Error when book by id were consulted", ex.getMessage());
			response.setMetadata("Response", "-1", "Operation failed");
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ModelResponseRest<Book>> createBook(Book book) {
		log.info("[1100] Book entry creation: Started");
		ModelResponseRest<Book> response =  new ModelResponseRest<>();
		List<Book> listBook = new ArrayList<Book>();
		try {
			Book bookSaved = bookDao.save(book);
			if(bookSaved != null) {
				listBook.add(bookSaved);
				response.getModelResponse().setModel(listBook);
				response.setMetadata("Response", "200", "Successful operation");
			}else {
				response.setMetadata("Response", "00", "Successful operation");
				return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.BAD_GATEWAY);
			}
		}catch(Exception ex) {
			log.error("[1100] Error when book tried to be created", ex.getMessage());
			response.setMetadata("Response", "-1", "Operation failed");
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("[1100] Book entry creation: Finished");
		return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ModelResponseRest<Book>> updateBook(Book book, Long id) {
		log.info("[1100] Book update information: Started");
		ModelResponseRest<Book> response =  new ModelResponseRest<>();
		List<Book> listBook = new ArrayList<Book>();
		try {
			Optional<Book> bookSearched = bookDao.findById(id);
			if(bookSearched.isPresent()) {
				bookSearched.get().setName(book.getName());
				bookSearched.get().setDescription(book.getDescription());
				bookSearched.get().setCategory(book.getCategory());
				Book bookUpdated = bookDao.save(bookSearched.get());
				if(bookUpdated != null) {
					listBook.add(bookUpdated);
					response.getModelResponse().setModel(listBook);
					response.setMetadata("Response OK", "200", "Updated Book");
				}else {
					log.error("Entry couldn´t be updated");
					response.setMetadata("Response", "00", "Unsuccessful operation");
					return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.BAD_GATEWAY);
				}
			}
		}catch(Exception ex) {
			log.error("[1100] Error when book tried to be created", ex.getMessage());
			response.setMetadata("Response", "-1", "Operation failed");
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("[1100] Book update information: Finished");
		return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ModelResponseRest<Book>> deleteBook(Long id) {
		log.info("[1100] Book delete: Started");
		ModelResponseRest<Book> response =  new ModelResponseRest<>();
		List<Book> listBooks = new ArrayList<Book>();
		try {
			log.info("[1100] Book delete: Started");
			bookDao.deleteById(id);
			response.getModelResponse().setModel(listBooks);
			response.setMetadata("Response", "200", "Successful operation");
		}catch(Exception ex) {
			log.error("[1100] Error when book was deleted", ex.getMessage());
			response.setMetadata("Response", "-1", "Operation failed");
			ex.printStackTrace();
			return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("[1100] Books delete: Finished");
		return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.OK);
	}

	
	
}
