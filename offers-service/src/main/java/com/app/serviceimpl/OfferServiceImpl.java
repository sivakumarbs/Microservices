package com.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Offers;
import com.app.repository.OffersRepository;
import com.app.service.IOfferService;

@Service
public class OfferServiceImpl implements IOfferService{

	@Autowired
	private OffersRepository repo;
	
	@Autowired
	private SequenceGeneratorService sequence;
	
	@Transactional
	@Override
	public Integer saveOffer(Offers offers) {
		offers.setOfferId(sequence.getSequenceNumber(Offers.SEQUENCE_NAME));
		return repo.save(offers).getOfferId();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Offers> getAllOffers() {
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Offers findByOfferCode(String code) {
		Optional<Offers> findByOfferCode = repo.findByOfferCode(code);
		if (findByOfferCode.isPresent()) {
			return findByOfferCode.get();
		}
		return null;
	}
	
	@Override
	public void deleteOffer(Integer id) {
		repo.deleteById(id);
	}
		
	@Override
	public boolean isExist(Integer id) {
		return repo.existsById(id);
	}
}
