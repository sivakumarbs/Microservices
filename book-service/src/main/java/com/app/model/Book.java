package com.app.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Document(collection  = "Booking_Ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	@Transient
    public static final String SEQUENCE_NAME = "book_sequence";
	
	@Id
	private Integer bookId;
	private Integer movieId;
	private String movieName;
	private Double ticketCost;
	
	private Integer noOfTickets;
	private List<Integer> seatNumbers;
	private String userEmail;
	private String userName;
	
	private Integer discount;
	private Double totalCost;

}
