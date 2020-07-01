package com.example.studentsmanagementsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.studentsmanagementsystem.exception.ClassroomNotFoundException;
import com.example.studentsmanagementsystem.exception.StudentNotFoundException;




@ControllerAdvice
public class ControllerExceptionalHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The student was not found in the system")
    @ExceptionHandler(StudentNotFoundException.class)
    public void studentNotFoundExceptionHandler(Exception exception) {
        logger.warn(exception.getMessage());
    }
	
	 @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The classroom was not found in the system")
	    @ExceptionHandler(ClassroomNotFoundException.class)
	    public void classroomNotFoundExceptionHandler(Exception exception) {
	        logger.warn(exception.getMessage());
	    }
	 
	 @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "There is no item to delete")
	    @ExceptionHandler(EmptyResultDataAccessException.class)
	    public void emptyResultDataAccessExceptionHandler(Exception exception) {
	        logger.warn(exception.getMessage());
	    }
	 
	 @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The request is invalid")
	    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	    public void methodArgumentTypeMismatchExceptionHandler(Exception exception) {
	        logger.warn(exception.getMessage());
	    }
}
