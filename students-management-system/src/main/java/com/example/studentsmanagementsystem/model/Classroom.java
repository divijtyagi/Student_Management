package com.example.studentsmanagementsystem.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("students")
public class Classroom {
	@Id
	private String code;
	private String title;
	private String description;
	@ManyToMany(mappedBy = "classrooms")
    private List<Student> students;
	public Classroom() {
		// TODO Auto-generated constructor stub
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 public List<Student> getStudents() {
	        return students;
	    }
	 public void setStudents(List<Student> students) {
	        this.students = students;
	    }
	

}
