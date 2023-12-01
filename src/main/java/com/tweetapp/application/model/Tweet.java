package com.tweetapp.application.model;

import java.util.List;

import org.apache.kafka.common.serialization.ListSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Builder
@Document(collection="Tweet")
public class Tweet {
	@Id
	private String username;
    private String tweetmessage;
    private int like;
    private String reply;
}
