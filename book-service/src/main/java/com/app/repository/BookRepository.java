package com.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
