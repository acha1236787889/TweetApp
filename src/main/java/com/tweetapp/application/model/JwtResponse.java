package com.tweetapp.application.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2340647315715265936L;
	private String token;

}
