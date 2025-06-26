package com.lcwd.hiber;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import com.lcwd.hiber.entities.Student;
import com.lcwd.hiber.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class StudentServices {
	private SessionFactory sessionfactory=HibernateUtil.getSessionFactory();
	public void saveStudent(Student student) {
		try(Session session=sessionfactory.openSession()) {
			Transaction beginTransaction=session.beginTransaction();
			session.persist(student);
			beginTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Student getById(long studentid) {
		try(Session session=sessionfactory.openSession())
		{
			Student student=session.get(Student.class,studentid);
			return student;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	//update Student Record
	public Student updateStudent(long studentid,Student student)
	{
		try (Session session=sessionfactory.openSession()){
			Transaction transaction=session.beginTransaction();
			Student oldstudent=session.get(Student.class,studentid);
			if(oldstudent!=null)
			{
				oldstudent.setName(student.getName());
				oldstudent.setFatherName(student.getFatherName());
				
				oldstudent=session.merge(oldstudent);
			}
			transaction.commit();
			return oldstudent;
			
		}
	}
	//Delete Student Record
	public void deleteStudent(long studentid)
	{
		try (Session session=sessionfactory.openSession())
		{
			Transaction transaction=session.beginTransaction();
			Student student=session.get(Student.class,studentid);
			if(student !=null)
			{
				session.remove(student);
			}
			transaction.commit();
		}
	}
	//get all students using HQL
	
	public List<Student> getAllStudentsHQL(){
		try (Session session=sessionfactory.openSession())
		{
			String getHQL="FROM Student";
			Query<Student> query=session.createQuery(getHQL,Student.class);
			return query.list();
		}
	}
	//Get student by name
	
	public Student getStudentbyNameHQL(String name) {
		try (Session session=sessionfactory.openSession()){
			String getByName="FROM Student WHERE name=:studentName";
			Query<Student> query=session.createQuery(getByName,Student.class);
			query.setParameter("studentName",name);
			return query.uniqueResult();
		}
	}
	//Criteria api
	//Get all Students of Same College
	
	public List<Student> getStudentByCollegeCriteria(String college)
	{
		try (Session session=sessionfactory.openSession()){
			HibernateCriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
			CriteriaQuery<Student> query=criteriaBuilder.createQuery(Student.class);
			Root<Student> root=query.from(Student.class);
			query.select(root).where(criteriaBuilder.equal(root.get("college"),college));
			Query<Student> query2=session.createQuery(query);
			return query2.getResultList();
		}
	}
	
	public List<Student> getStudentwithPagination(int pageno,int pagesize)
	{
		try (Session session=sessionfactory.openSession()){
			String pagiQuery="FROM Student";
			Query<Student > query=session.createQuery(pagiQuery,Student.class);
			query.setFirstResult((pageno-1)*pagesize);
			query.setMaxResults(pagesize);
			return query.list();
			
		}
	}
}

