package com.augustobr.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.augustobr.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String>{

}
