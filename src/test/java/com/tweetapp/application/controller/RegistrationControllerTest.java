package com.tweetapp.application.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

//import javax.servlet.Registration;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tweetapp.application.exception.NotAvaliableException;
import com.tweetapp.application.model.Registration;
import com.tweetapp.application.model.Reply;
import com.tweetapp.application.model.Tweet;
import com.tweetapp.application.repo.TweetRepo;
import com.tweetapp.application.service.RegistrationService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegistrationControllerTest {
	
	@Mock
	private RegistrationService registrationService;
	@Mock
	private TweetRepo tweetRepo;
	@InjectMocks
	private RegistrationController registrationController;
	
	@Test
	public void testRegistration() {
		Registration r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		
		ResponseEntity<String> k=registrationController.doRegister(r);
		assertNotNull(k);
	}
	
	@Test
	public void testGetUser() {
		Registration r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		List<Registration> l=new ArrayList();
		l.add(r);
		when(registrationService.getAllUsers()).thenReturn(l);
		ResponseEntity<List<Registration>> o=registrationController.getAllUser();
		//System.out.print(o.getBody().get(0).getFirst_name());
		assertNotNull(o);
		
	}
	
	@Test
	public void testLogin() {
		String username="dsdcs";
		String password="Dcssd";
		boolean k=false;
		when(registrationService.login(username, password)).thenReturn(true);
		ResponseEntity<String> re=registrationController.login(username, password);
		//System.out.println(re.getBody());
		assertEquals("Login Successful", re.getBody());
	}
	
	@Test
	public void testSearchByUsername() throws NotAvaliableException {
		Registration r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		String username="scssd";
		when(registrationService.searchByUsername(username)).thenReturn(r);
		ResponseEntity<Registration> re=registrationController.getByUserName(username);
		assertNotNull(re.getBody());
	}
	
	@Test
	public void testTweetPost() {
		List<String>l=new ArrayList();
		l.add("dss");
	    Tweet tr=new Tweet("scssd","dss",0,null);
	    ResponseEntity<String> rw=registrationController.postnewTweet(tr);
	    assertNotNull(rw.getBody());
	}
	
	@Test
	public void testAllTweet() {
		List<String>l=new ArrayList();
		l.add("dss");
	    Tweet tr=new Tweet("scssd","dss",0,null);
		List<Tweet>lt =new ArrayList();
		lt.add(tr);
		when(registrationService.getAllTweets()).thenReturn(lt);
		ResponseEntity<List<Tweet>> qw=registrationController.getAllTweet();
		assertEquals(0, qw.getBody().get(0).getLike());
	}
	@Test
	public void testDeleteTweet() {
		List<String>l=new ArrayList();
		l.add("dss");
	    Tweet tr=new Tweet("scssd","dss",0,null);
	    tweetRepo.save(tr);
	    ResponseEntity<String> tw=registrationController.deleteTweet("scssd");
	    assertEquals("tweet deleted",tw.getBody() );
	}
	
	@Test
	public void testLikeTweet() {
		List<String>l=new ArrayList();
		l.add("dss");
	    Tweet tr=new Tweet("scssd","dss",0,null);
	    tweetRepo.save(tr);
	    ResponseEntity<String> p=registrationController.likeTweet("scssd");
	    assertEquals("Liked",p.getBody());
	}
	@Test
	public void testReplyTweet() {
		Reply r=new Reply("Dsds","dssd","dss");
		ResponseEntity<String> x=registrationController.replyTweet(r);
		assertNotNull(x);
	}
	
	

}
