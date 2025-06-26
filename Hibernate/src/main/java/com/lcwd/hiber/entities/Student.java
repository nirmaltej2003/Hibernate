package com.lcwd.hiber.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class Student 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long studentid;
	@Column(name="Student_name",length=100,unique=true)
	private String name;
	@Column(name="Student_college",length=200,nullable=true)
	private String college;
	private String phone;
	private String fatherName;
	private boolean active=true;
	@OneToMany(mappedBy = "student",fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Certificate> certificates=new ArrayList<Certificate>();
	@Lob
	private String about;
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public List<Certificate> getCertificates() {
		return certificates;
	}
	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}
	
}
