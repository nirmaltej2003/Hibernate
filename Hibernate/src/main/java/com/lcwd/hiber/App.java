package com.lcwd.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lcwd.hiber.entities.Certificate;
import com.lcwd.hiber.entities.Student;
import com.lcwd.hiber.util.HibernateUtil;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Student student=new Student();
        student.setName("Nancy Jewel Mc Donie");
        student.setCollege("IIT");
        student.setPhone("888745288");
        student.setFatherName(" McDonie ");
        student.setActive(true);
        student.setAbout("Actress");
        
        Certificate certificate=new Certificate();
    	certificate.setTitle("Java Certification");
    	certificate.setAbout("This is java certification");
    	certificate.setLink("Link");
    	certificate.setStudent(student);
    	
    	 Certificate certificate1=new Certificate();
     	certificate1.setTitle("Python Certification");
     	certificate1.setAbout("This is Python certification");
     	certificate1.setLink("Link");
     	certificate1.setStudent(student);
     	
     	 student.getCertificates().add(certificate);
     	 student.getCertificates().add(certificate1);
     	 
     	
        SessionFactory sessionfactory=HibernateUtil.getSessionFactory();
        System.out.println(sessionfactory);
       Session session= sessionfactory.openSession();
       Transaction transaction=null;
       try {
    	   transaction=session.beginTransaction();
    	   session.persist(student);
    	   transaction.commit();
    	   System.out.println("Student saved successfully");
	} catch (Exception e) {
		if(transaction!=null)
		{
			transaction.rollback();
		}
			e.printStackTrace();
	}finally {
		session.close();
	}
        SessionFactory sessionfactory1=HibernateUtil.getSessionFactory();
        System.out.println(sessionfactory1);
    }
}
