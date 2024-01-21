package com.tweetapp.application.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.application.exception.InvalidFieldException;
import com.tweetapp.application.exception.NotAvaliableException;
import com.tweetapp.application.model.ForgotPassword;
import com.tweetapp.application.model.Login;
import com.tweetapp.application.model.Registration;
import com.tweetapp.application.model.Reply;
import com.tweetapp.application.model.Tweet;
import com.tweetapp.application.model.UpdateTweet;
import com.tweetapp.application.service.RegistrationService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.experimental.Delegate;
//import ch.qos.logback.classic.Logger;
import lombok.extern.log4j.Log4j;

@RestController
public class RegistrationController {
	 Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping("/api/v1.0/tweets/register")
	public ResponseEntity<String> doRegister(@RequestBody Registration r) {
		
		try {
			registrationService.doRegistration(r);
		    //logger.info("Successfully Registered");
		    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully Registered");
		} catch (InvalidFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Registration Terminated");
		}
	}
	@GetMapping("/api/v1.0/tweets/users/all")
	public  ResponseEntity<List<Registration>> getAllUser(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(registrationService.getAllUsers());
	}
	
	@GetMapping("/api/v1.0/tweets/login/{username}/{password}")
	public ResponseEntity<String> login(@PathVariable String username,@PathVariable String password){
		if(registrationService.login(username, password)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login Successful");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Login Failed");
		}
		
	}
	@PostMapping("/api/v1.0/tweets/login")
	public ResponseEntity<String> login(@RequestBody Login l){
		if(registrationService.login2(l)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login Successful");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Login Failed");
		}
		
	}
	@GetMapping("/api/v1.0/tweets/user/search/{username}")
	public ResponseEntity<Registration> getByUserName(@PathVariable String username){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(registrationService.searchByUsername(username));
		} catch (NotAvaliableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
	}
	@PostMapping("/api/v1.0/tweets")
	public ResponseEntity<String> postnewTweet(@RequestBody Tweet t){
		try {
			registrationService.postmessage(t);
			System.out.println(t.getUsername());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Message Posted");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message Posting Failed");
		}
	}
	@GetMapping("/api/v1.0/tweets/username")
	public ResponseEntity<List<Tweet>> getAllTweet(){
		return ResponseEntity.status(HttpStatus.OK).body(registrationService.getAllTweets());
	}
	@PutMapping("/api/v1.0/tweets/{username}/forgot")
	public ResponseEntity<String> forgotPassword(@PathVariable String username,@RequestBody ForgotPassword f){
		try {
			registrationService.doForget(username, f);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password changed");
		} catch (NotAvaliableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Password change failed");
		}
		
	}
	

	
	@PutMapping("/api/v1.0/tweets/{username}/update")
	public ResponseEntity<String> updateTweet(@PathVariable String username,@RequestBody UpdateTweet ut){
		try {
			registrationService.updateTweet(username, ut);
			return ResponseEntity.status(HttpStatus.OK).body("tweet updated");
		} catch (NotAvaliableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body("tweet not updated");
		}
		
			
	}
	
	
	@DeleteMapping("/api/v1.0/tweets/{username}/delete")
	public ResponseEntity<String> deleteTweet(@PathVariable String username){
		try {
			registrationService.deleteTweet(username);
			return ResponseEntity.status(HttpStatus.OK).body("tweet deleted");
		}
		catch(NotAvaliableException e ) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body("tweet not deleted");
		}
	}
	@PutMapping("/api/v1.0/tweets/{username}/like")
	public ResponseEntity<String> likeTweet(@PathVariable String username){
		try {
			registrationService.likeTweet(username);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Liked");
		} catch (NotAvaliableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Not able to like");
		}
		
	}
	
	@PostMapping("/api/v1.0/tweets/reply")
	public ResponseEntity<String> replyTweet(@RequestBody Reply r){
		try {
			registrationService.replyTweet(r);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Reply added");
		} catch (NotAvaliableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Reply not added");
		}
	}
	
	@GetMapping("/api/v1.0/tweets/logut")
	public ResponseEntity<String> logout(){
		registrationService.logout();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logged Out");
	}
	

}
