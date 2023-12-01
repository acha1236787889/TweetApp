package com.tweetapp.application.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tweetapp.application.model.Registration;

public interface RegistrationRepo extends MongoRepository<Registration, String> {
    @Query("{login_id=?0}")
	public Registration findByUserName(String login_id);
}
