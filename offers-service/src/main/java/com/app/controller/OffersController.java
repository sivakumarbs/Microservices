package com.app.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Offers;
import com.app.service.IOfferService;

@RestController
@RequestMapping("offers")
public class OffersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OffersController.class);

	@Autowired
	private IOfferService service;

	@GetMapping("msg")
	public String msg() {
		return "Offers Service";
	}

	@PostMapping("create")
	public ResponseEntity<String> makeOffer(@RequestBody Offers offers) {
		ResponseEntity<String> resp = null;
		try {
			service.saveOffer(offers);
			resp = new ResponseEntity<String>("Offer is created succesfully...",HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			resp = new ResponseEntity<String>("Internal server error..",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("get/{code}")
	public ResponseEntity<?> getOffer(@PathVariable("code") String offerCode ) throws ParseException {
		ResponseEntity<?> resp = null;
		Offers offer = service.findByOfferCode(offerCode);

		try {
			if(offer!=null) {
				resp = new ResponseEntity<Double>(offer.getAmount(),HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>("Invalid offer code...",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("getall")
	public ResponseEntity<String> getAllOffers() {
		ResponseEntity<?> resp = null;
		try {
			List<Offers> offers = service.getAllOffers();
			if(offers!=null && !offers.isEmpty())
				resp = new ResponseEntity<List<Offers>>(offers,HttpStatus.OK);	
			else
				resp = new ResponseEntity<String>("No offers found....",HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			resp = new ResponseEntity<String>("Internal server error....",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (ResponseEntity<String>) resp;
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteOffer(@PathVariable("id") Integer offerId) {
		ResponseEntity<String> resp = null;
		try {
			service.deleteOffer(offerId);
			resp = new ResponseEntity<String>("Offer code is deleted succesfully",HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			resp = new ResponseEntity<String>("Unable to delete",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
}
