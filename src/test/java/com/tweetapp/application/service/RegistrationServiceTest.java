package com.tweetapp.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tweetapp.application.exception.InvalidFieldException;
import com.tweetapp.application.exception.NotAvaliableException;
import com.tweetapp.application.model.Registration;
import com.tweetapp.application.repo.RegistrationRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegistrationServiceTest {
	@Mock
	private RegistrationService registrationService;
	@Mock
	private RegistrationRepo registrationRepo;
	
	@Test
	public void testRegistration() throws InvalidFieldException {
		Registration r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		registrationService.doRegistration(r);
		assertNotNull(registrationRepo.findAll());
	}
	@Test
	public void testGetAllUsers() {
		Registration r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		List<Registration> l=new ArrayList();
		l.add(r);
		when(registrationService.getAllUsers()).thenReturn(l);
		List<Registration> rl=registrationService.getAllUsers();
		assertEquals("scssd",rl.get(0).getLogin_id());
	}
	@Test
	public void testLogin() {
		 String username="dada";
		 String password="dss";
		 when(registrationService.login(username, password)).thenReturn(true);
		 boolean b=registrationService.login(username, password);
		 assertTrue(b);
		 
	}
	@Test
	public void testGetByUsername() throws NotAvaliableException {
		Registration r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		when(registrationService.searchByUsername("scssd")).thenReturn(r);
		Registration h=registrationService.searchByUsername("scssd");
		assertEquals("SDssdds", h.getFirst_name());
		
	}
	

}
