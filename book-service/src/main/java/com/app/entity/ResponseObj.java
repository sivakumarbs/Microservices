package com.app.entity;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObj {
	
	private String message;
	private HttpStatus status;
	private List<Object> object;
	
}
