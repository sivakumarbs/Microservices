package com.app.request.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.entity.MovieUpdateRequest;
import com.app.entity.ResponseObj;

@Service
public class MovieRequests {

	RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity<ResponseObj> updateMovieTickets(MovieUpdateRequest request) {
		ResponseEntity<ResponseObj> response = null;
		try {
			String url = "http://localhost:2008/movie/update";
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<MovieUpdateRequest> httpEntity = new HttpEntity<>(request,httpHeaders);	
			 response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResponseObj.class);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity<ResponseObj>(new ResponseObj("Movie service is down, please try later....", HttpStatus.INTERNAL_SERVER_ERROR, null),HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return response;
	}
}
