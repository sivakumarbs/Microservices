package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Book;
import com.app.service.IBookService;

@RestController
@RequestMapping("book")
public class BookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private IBookService service;

	@GetMapping("msg")
	public String message() {
		return "Order Service";
	}


	@SuppressWarnings("null")
	@PostMapping("ticket")
	public ResponseEntity<String> bookTicket(@RequestBody Book book) {
		ResponseEntity<String> response = null;
		try {
			Integer bookTicket = service.bookTicket(book);
			if(bookTicket !=null || bookTicket !=0) {
				response = new ResponseEntity<String>(book.getMovieName()+" Movie ticket is booked succesfully",HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<String>("Unable to process book request...",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("getbookings")
	public ResponseEntity<?> getAllBookings() {

		ResponseEntity<?> resp = null;
		try {
			List<Book> bookings = service.getAllBookings();
			if(bookings!=null && !bookings.isEmpty())
				resp = new ResponseEntity<List<Book>>(bookings,HttpStatus.OK);	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

}
