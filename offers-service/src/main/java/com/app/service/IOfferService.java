package com.app.service;

import java.util.List;

import com.app.model.Offers;

public interface IOfferService {
	
	public Integer saveOffer(Offers offers);
	public List<Offers> getAllOffers();
	public Offers findByOfferCode(String code);
	public void deleteOffer(Integer id);
	public boolean isExist(Integer id);
}
