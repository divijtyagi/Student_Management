package com.example.studentsmanagementsystem.exception;

public class ClassroomNotFoundException extends Exception {
	public ClassroomNotFoundException( String code)
	{
		super("Record for classroom with code " + code + " not found");
	}

}
