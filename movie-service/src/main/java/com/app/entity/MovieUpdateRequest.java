package com.app.entity;

import lombok.Data;

@Data
public class MovieUpdateRequest {
	
	private Integer movieId;
	private Integer bookedTickets;
}
