package com.service;
import com.pojo.User;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class AuthService 
{

	//HibernateTemplate Ref
    private	HibernateTemplate hibernateTemplate;
    private static Logger log = Logger.getLogger(AuthService.class);


    
    
    //setter and getter for hibernatetemplate property
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
    
	
	
	//service class method to find the user login details from the database
	public boolean findUser(String uname,String upwd)
	
	{
		 log.info("Checking the user in the database");

		//boolean variable t validate db details from hibernate
		boolean isVaildUser  = false;
		String qureytodata =  "from User u where  u.name = ?  and u.password = ?";	
		try
		{
			List userObject =  (List)   hibernateTemplate.find(qureytodata,uname,upwd);
			
			//decison making for null value 
			if ( userObject != null &&  userObject.size()  >0)
			{			
				
				 log.info("Id= " + userObject.get(0));
				isVaildUser = true;				
			}			
		}
		catch(Exception e)
		{
			isVaildUser = false;
			log.error("An error occurred while fetching the user details from the database", e);
		}
		return isVaildUser;		
		
	}
    
   
    
    
	
}
