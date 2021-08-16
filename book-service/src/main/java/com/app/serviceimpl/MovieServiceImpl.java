package com.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.MovieUpdateRequest;
import com.app.entity.ResponseObj;
import com.app.model.Book;
import com.app.repository.BookRepository;
import com.app.request.service.MovieRequests;
import com.app.service.IBookService;

@Service
public class MovieServiceImpl implements IBookService {

	@Autowired
	private BookRepository repo;

	@Autowired
	private MovieRequests request;
	
	@Autowired
	private SequenceGeneratorService seq;

	@Transactional
	@Override
	public Integer bookTicket(Book book) {

		MovieUpdateRequest movieUpdateRequest = new MovieUpdateRequest();

		if(book.getMovieId() != null || book.getMovieId() != 0 ) {	
			movieUpdateRequest.setMovieId(book.getMovieId());
			movieUpdateRequest.setBookedTickets(book.getNoOfTickets());
			ResponseEntity<ResponseObj> response = request.updateMovieTickets(movieUpdateRequest);
			if (response.getBody().getStatus() == HttpStatus.OK) {
				book.setBookId(seq.getSequenceNumber(Book.SEQUENCE_NAME));
				Book save = repo.save(book);
				return save.getBookId();
			}

		}

		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Book> getAllBookings() {
		return repo.findAll();
	}

}
