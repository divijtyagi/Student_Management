package com.example.studentsmanagementsystem.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import javax.persistence.JoinColumn;


@Entity
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String firstName;
	private String lastName;
	@ManyToMany
	@JoinTable(name = "student_classroom",
			joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_code", referencedColumnName = "code"))
    private List<Classroom> classrooms;
	
	public List<Classroom> getClassrooms() {
        return classrooms;
    }
	 public void setClassrooms(List<Classroom> classrooms) {
	        this.classrooms = classrooms;
	    }

	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 public void addClassroom(Classroom classroom) {
	        classrooms.add(classroom);
	    }
	    public void removeClassroom(Classroom classroom) {
	        classrooms.remove(classroom);
	    }
}