package com.example.studentsmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.studentsmanagementsystem.model.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findStudentsByFirstNameContainingIgnoreCase(String firstName);
	List<Student> findStudentsByLastNameContainingIgnoreCase(String lastName);
	 @Query("SELECT student FROM Student student WHERE LOWER(student.firstName) LIKE LOWER(:firstName) AND LOWER(student.lastName) LIKE LOWER(:lastName)")
	    List<Student> findStudentsByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
