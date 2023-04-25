package com.cos.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.book.dto.Book;
import com.cos.book.sevice.BookService;

import jakarta.websocket.server.PathParam;

@RestController
public class BookRestController {
	
	@Autowired
    private BookService bookService;
	
	@PostMapping("/book")
	public ResponseEntity<?> save(@RequestBody Book book){
		return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
	}

	@GetMapping("/book")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(bookService.findAllBook(), HttpStatus.OK);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<?> finById(@PathVariable Long id){
		return new ResponseEntity<>(bookService.findBook(id), HttpStatus.OK);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Book book){
		return new ResponseEntity<>(bookService.modifyBook(id, book), HttpStatus.OK);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, @RequestBody Book book){
		return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
	}

}
