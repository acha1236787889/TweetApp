package com.tweetapp.application.model;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTweet {
	@Id
	private String username;
	private String tweetMessagetoBeUpdated;
    private String tweetmessage;
}
