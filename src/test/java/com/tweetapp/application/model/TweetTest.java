package com.tweetapp.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TweetTest {
	//List<String>l=new ArrayList();
	
	Tweet t=new Tweet("sax","dssda",0,"sxsx");
	
	@Test
    public void testUsername() {
    	assertEquals("sax", t.getUsername());
    }
	@Test
    public void testTweetMessage() {
    	assertEquals("dssda", t.getTweetmessage());
    }
	@Test
    public void testLike() {
    	assertEquals(0, t.getLike());
    }
	@Test
    public void testReply() {
    	assertEquals("sxsx", t.getReply());
    }
}
