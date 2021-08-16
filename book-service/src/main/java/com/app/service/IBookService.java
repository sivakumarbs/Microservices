package com.app.service;

import java.util.List;

import com.app.model.Book;

public interface IBookService {
	
	public Integer bookTicket(Book book);
	public List<Book> getAllBookings();

}
