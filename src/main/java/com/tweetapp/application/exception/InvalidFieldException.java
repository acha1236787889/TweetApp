package com.tweetapp.application.exception;

public class InvalidFieldException  extends Exception  
{  
    public InvalidFieldException (String str)  
    {  
        // calling the constructor of parent Exception  
        super(str);  
    }  
}  
