package com.lcwd.hiber.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil 
{
	private static SessionFactory sessionfactory;
	static {
		try {
			if(sessionfactory==null)
			{
				sessionfactory=new Configuration()
						.configure("hibernate.cfg.xml").buildSessionFactory();
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error in creating :"+e.getMessage());
		}
	}
	public static SessionFactory getSessionFactory(){
		return sessionfactory;
	}
}
