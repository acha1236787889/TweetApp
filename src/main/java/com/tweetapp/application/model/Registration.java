package com.tweetapp.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection="Registration")
public class Registration {
	
	@Id
	private String login_id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String cnf_password;
	private  Integer contact_number;
	

}
