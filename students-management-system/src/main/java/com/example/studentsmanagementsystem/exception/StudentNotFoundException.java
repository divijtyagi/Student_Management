package com.example.studentsmanagementsystem.exception;

public class StudentNotFoundException extends Exception  {
	public StudentNotFoundException(int studentId)
	{
		super("Record for student with id " + studentId + " not found");
	}

}
