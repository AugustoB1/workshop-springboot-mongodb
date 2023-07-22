package com.augustobr.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.augustobr.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
