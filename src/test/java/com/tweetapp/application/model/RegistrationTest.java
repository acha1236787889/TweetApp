package com.tweetapp.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegistrationTest {
	
	Registration r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
	@Before
	public void setup(){
		// r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		 System.out.println(r.getCnf_password());
	}
	
	@Test
	public void testLoginId() {
		//r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		assertEquals("scssd",r.getLogin_id());
	}
	
	@Test
	public void testFirst_Name() {
		//r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		assertEquals("SDssdds",r.getFirst_name());
	}
	
	@Test
	public void testLast_Name() {
		//r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		assertEquals("dsdssd",r.getLast_name());
	}
	@Test
	public void testEmail() {
		//r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		assertEquals("sdsds@rt.com",r.getEmail());
	}
	
	@Test
	public void testPassword() {
		//r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		assertEquals("abc",r.getPassword());
	}
	
	@Test
	public void testConfPwd() {
		//r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		assertEquals("abc",r.getCnf_password());
	}
	
	@Test
	public void testContact() {
		//r=new Registration("scssd","SDssdds","dsdssd","sdsds@rt.com","abc","abc",1234);
		assertEquals(1234,r.getContact_number());
	}
	
	
	
	
	
	

}
