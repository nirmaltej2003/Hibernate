package com.lcwd.hiber.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="student_Certificate")
public class Certificate 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long certificateid;
	private String about;
	private String title;
	private String link;
	
	
	public long getCertificateid() {
		return certificateid;
	}


	public void setCertificateid(long certificateid) {
		this.certificateid = certificateid;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
}
