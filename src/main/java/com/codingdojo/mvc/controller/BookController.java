package com.codingdojo.mvc.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	//instance of service
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService=bookService;
	}
	
	//render all books API
	@RequestMapping("")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	
	
	//creating book API
	@RequestMapping(value="/create" , method=RequestMethod.POST)
	public Book create(@ModelAttribute("book") Book book) {
		
		//create instance of create book inside service and give it the argument that inside Book model  
		Book createbook=bookService.createBook(book);
		return createbook;
	}
	
	
	
		//get book by id 
	@RequestMapping("/{bookId}")
	public Book viewBook(@PathVariable("bookId") Long bookId){
		Optional<Book> bookOptional =bookService.getBookById(bookId);
		Book book=bookOptional.get();
		return book; 
		}
	
	//delete book
		
	 @RequestMapping(value="/delete/{bookId}", method=RequestMethod.DELETE)
    public boolean destroy(@PathVariable("bookId") Long bookId) {
	       return bookService.deleteBook(bookId);
	    }
		
 	@RequestMapping(value="/update/{bookId}", method=RequestMethod.PUT)
    public Book update(@PathVariable("bookId") Long bookId,@ModelAttribute("book") Book book ) {
 		return bookService.updateBook(bookId,book); 
        
    }
		    
		    
	

}
