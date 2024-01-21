package com.tweetapp.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tweetapp.application.model.ForgotPassword;
import com.tweetapp.application.model.Login;
import com.tweetapp.application.model.Registration;
import com.tweetapp.application.model.Reply;
import com.tweetapp.application.model.Tweet;
import com.tweetapp.application.model.UpdateTweet;
import com.tweetapp.application.repo.RegistrationRepo;
import com.tweetapp.application.repo.TweetRepo;
import com.tweetapp.application.util.TweetAppUtility;

import ch.qos.logback.classic.Logger;

import com.tweetapp.application.exception.*;

@Service
public class RegistrationService {
    @Autowired
	RegistrationRepo repo;
    @Autowired
    TweetRepo tweetRepo;
    
    public boolean h1;
    public String u1;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, List<Tweet>> kafkaTemplate1;
    
    public void checkNull(Registration r) throws InvalidFieldException{
    	if(r.getPassword().isEmpty()) {
    		throw new InvalidFieldException(TweetAppUtility.PASSWORD_MANDATORY);
    	}
    	if(r.getLogin_id().isEmpty()) {
    		throw new InvalidFieldException(TweetAppUtility.LOGINID_MANDATORY);
    	}
    	if(r.getLast_name().isEmpty()) {
    		throw new InvalidFieldException(TweetAppUtility.LASTNAME_MANDATORY);
    	}
    	if(r.getFirst_name().isEmpty()) {
    		throw new InvalidFieldException(TweetAppUtility.FIRSTNAME_MANDATORY);
    	}
    	if(r.getEmail().isEmpty()) {
    		throw new InvalidFieldException(TweetAppUtility.EMAIL_MANDATORY);
    	}
    	
    	if(r.getContact_number()==null) {
    		throw new InvalidFieldException(TweetAppUtility.CONTACTNUMBER_MANDATORY);
    	}
    	if(r.getCnf_password().isEmpty()) {
    		throw new InvalidFieldException(TweetAppUtility.PASSWORD_MANDATORY);
    	}
    }
    
    public void doRegistration(Registration r) throws InvalidFieldException{
    	Optional<Registration> p=repo.findById(r.getLogin_id())   ; 
    	if(p.isPresent()) {
    		throw new InvalidFieldException("this login id already exist. try new one");
    	}
    	else if(!r.getPassword().equals(r.getCnf_password())) {
    		throw new InvalidFieldException("Password and Confirm password should match");
    	}
    	else if(r.getEmail().equals(r.getLogin_id())) {
    		throw new InvalidFieldException("email and login id must be different");
    	}
       checkNull(r);
       repo.save(r);
    }
    
    public List<Registration> getAllUsers(){
    	return repo.findAll();
    }
    
    public boolean login(String h,String p) {
    	if(repo.findById(h).isPresent()) {
    		Registration o=repo.findById(h).get();
    		if(o.getPassword().equals(p)) {
    			h1=true;
    			u1=o.getLogin_id();
    			return true;
    		}
    	}
    	h1=false;
    	return false;
    }
    public Registration searchByUsername(String username) throws NotAvaliableException {
    	if(!repo.findById(username).isPresent()) {
    		throw new NotAvaliableException("Username does not exist");
    	}
    	Registration r=repo.findById(username).get();
    	return r;
    }
  
public void postmessage(Tweet t1) throws NotAvaliableException {
	   Tweet t=new Tweet();
	   System.out.println(h1);
	   if(h1==true ) {
		   Tweet y=new Tweet();
		   Optional<Tweet> j=tweetRepo.findById(t1.getUsername());
		   if(!(j==null)) {
			   System.out.println(tweetRepo.findById(t1.getUsername()));
			   if(tweetRepo.findById(t1.getUsername()).isPresent()) {
		    y=tweetRepo.findById(t1.getUsername()).get();
			   }
		   }
		  System.out.print(t1.getUsername());
		   t.setUsername(t1.getUsername());
		   List<String> l=new ArrayList();
		   String l1 = null;
		   if(y!=null && y.getTweetmessage()!=null) {
		   l1=y.getTweetmessage();
		   }
		   if(l1!=null) {
	       l1=l1+t1.getTweetmessage()+",";
		   }
		   else {
			   l1=t1.getTweetmessage()+",";
		   }
		  t.setTweetmessage(l1);
		   
	   }
	   else {
		   throw new NotAvaliableException("Please Login first");
	   }
	   tweetRepo.save(t);
	   String p=t1.getTweetmessage();
	   
   }

 public boolean login2(Login l)
{
	 if(repo.findById(l.getUsername()).isPresent()) {
 		Optional<Registration> o=repo.findById(l.getUsername());
 		if(o.get().getPassword().equals(l.getPassword())) {
 			h1=true;
 			 u1=l.getUsername();
 			return true;
 		}
 	}
 	h1=false;
 	return false;
}
 public List<Tweet> getAllTweets(){
	 return tweetRepo.findAll();
	 
	 
	 
 }
 public void doForget(String username,ForgotPassword fg) throws NotAvaliableException {
	 Optional<Registration> o=repo.findById(u1);
	 if(!o.isPresent()) {
		 throw new NotAvaliableException("The username does not exist");
	 }
	 else {
		 o.get().setPassword(fg.getNewPassword());
		 o.get().setCnf_password(fg.getNewPassword());
	 }
	 repo.save(o.get());
 }
 
 public void updateTweet(String username,UpdateTweet u) throws NotAvaliableException {
	if(h1==true) {
		if(username.equals(u.getUsername()) && u1.equals(u.getUsername())) {
		Tweet t=tweetRepo.findById(username).get();
		
		String m=t.getTweetmessage();
		if(m.contains(u.getTweetMessagetoBeUpdated())) {
		m=m.replace(u.getTweetMessagetoBeUpdated(), u.getTweetmessage());
		}
		else {
			throw new NotAvaliableException("The tweet is not present to be updated");
		}

		t.setTweetmessage(m);
		tweetRepo.save(t);
		}
	}
	else if(h1==false) {
		throw new NotAvaliableException("Login Please");
	}
	else {
		throw new NotAvaliableException("Username does not exist");
	}
 }
 
 public void deleteTweet(String username) throws NotAvaliableException {
	 Optional<Tweet> t=tweetRepo.findById(username);
	 System.out.print(t);
	 if(h1==true && username.equals(u1)) {
		 
		 if(t.isPresent()) {
			 tweetRepo.deleteById(username);
		 }
			
		}
		else if(h1==false) {
			throw new NotAvaliableException("Login Please");
		}
		else if(!t.isPresent()){
			throw new NotAvaliableException("Username does not exist");
		}
 }
 
  public void likeTweet(String username) throws NotAvaliableException {
	  if(h1==true) {
	Tweet t=tweetRepo.findById(username).get();
	 int like=t.getLike();
	 like++;
	 t.setLike(like);
	 tweetRepo.save(t);
	  }
	  else {
		  throw new NotAvaliableException("Login First");
	  }
  }
  
  public void replyTweet(Reply r) throws NotAvaliableException {
	  if(h1==true) {
		  Optional<Tweet> t=tweetRepo.findById(r.getUsername());
		  if((t==null)) {
			  throw new NotAvaliableException("username does not exist");
		  }
		  else if(t.get().getTweetmessage().contains(r.getTweetmessage())){
			  List<String> l=new ArrayList<>();
			  l.add(r.getReply()+":"+u1+":"+r.getTweetmessage());
			  
			  t.get().setReply(r.getReply()+":"+u1+":"+r.getTweetmessage());
			  tweetRepo.save(t.get());
		  }
		  else if(!(t.get().getTweetmessage().contains(r.getTweetmessage()))) {
			  throw new NotAvaliableException("This tweet does not exist");
		  
	  }
	  }
		  
	  else  {
		  throw new NotAvaliableException("Login First");
	  }
	  String d=r.getReply()+":"+u1+":"+r.getTweetmessage();
  }
  
  public void logout() {
	  h1=false;
  }
 
}


