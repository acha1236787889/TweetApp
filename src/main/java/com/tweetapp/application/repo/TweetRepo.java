package com.tweetapp.application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.application.model.Tweet;

public interface TweetRepo extends MongoRepository<Tweet, String>{

}
