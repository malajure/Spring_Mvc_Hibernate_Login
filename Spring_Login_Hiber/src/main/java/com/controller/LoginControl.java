
package com.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.service.AuthService;


@Controller
@RequestMapping("/user")
public class LoginControl 
{
	
	@Autowired
	private AuthService authenticateService;  //binding the service class ref
	
	private static Logger log = Logger.getLogger(LoginControl.class);

	//check the user  login details for correct or wrong
	
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public ModelAndView validateUser(@RequestParam("username") String username , @RequestParam("password") String  password)
	{
			
		String message ="";
		//boolean variable for testing the validation of the user login
		boolean isValid =  authenticateService.findUser(username, password);		
		 log.info("Is user valid?= " + isValid);
		if(isValid)
		{
			message = "Welcome " + username  +"  !!!";
		}else 
		{
			message = "Invalid  Credentials";
			
		}		
		
		//modelandview(viewname,objectname,objectvalue);
		return  new ModelAndView("result","output",message);
	}
	
 
	
	 
	
	
	
}
