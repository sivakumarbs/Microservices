package com.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.Offers;

public interface OffersRepository extends MongoRepository<Offers, Integer>{
	
	Optional<Offers> findByOfferCode(String offerCode);

}
