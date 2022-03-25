package com.company.books.backend.services;

import java.util.ArrayList;
import java.util.List;

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
			log.info("[1100] Inicio consulta libros: ");
			listBooks = (List<Book>) bookDao.findAll();
			response.getModelResponse().setModel(listBooks);
			response.setMetadata("Response", "200", "Successful operation");
		}catch(Exception ex) {
			log.error("[1100] Error when books were consulted", ex.getMessage());
			response.setMetadata("Response", "-1", "Operation failed");
			ex.printStackTrace();
		}
		return new ResponseEntity<ModelResponseRest<Book>>(response, HttpStatus.OK);
	}

	
	
}
