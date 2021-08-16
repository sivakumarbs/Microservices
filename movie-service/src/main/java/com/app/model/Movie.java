package com.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Movie_Tab")
public class Movie {

	@Id
	@GeneratedValue
	private Integer movieId;
	private String movieName;
	private String movieCode;
	private String imageUrl;
	private String descripion;
	private Integer totalTickets;
	private Double ticketCost;
	@Temporal(TemporalType.DATE)
	private Date date;

}
