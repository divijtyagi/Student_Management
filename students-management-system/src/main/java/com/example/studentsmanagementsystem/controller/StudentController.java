package com.example.studentsmanagementsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.studentsmanagementsystem.exception.ClassroomNotFoundException;
import com.example.studentsmanagementsystem.exception.StudentNotFoundException;
import com.example.studentsmanagementsystem.model.Classroom;
import com.example.studentsmanagementsystem.model.Student;
import com.example.studentsmanagementsystem.repository.ClassroomRepository;
import com.example.studentsmanagementsystem.repository.StudentRepository;

//{
//    "id": 1,
//    "firstName": "rohit",
//    "lastName": "Tyagi",
//    "classrooms":[{
//   "code": "BT1003",
//   "title": "cse-b",
//   "description":"computer science"
//}]
//
//}
@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ClassroomRepository classroomRepository;
	
	@GetMapping()
	private List<Student> getStudentByName(@RequestParam(value = "firstName", required = false) String firstName,
                                     @RequestParam(value = "lastName", required = false) String lastName){
		 if(firstName!=null && lastName!=null)   return studentRepository.findStudentsByFirstAndLastName(firstName, lastName);
		 else if(firstName!=null)   return studentRepository.findStudentsByFirstNameContainingIgnoreCase(firstName);
		 else if(lastName!=null)   return studentRepository.findStudentsByLastNameContainingIgnoreCase(lastName);
		 else   return studentRepository.findAll();
	}
	
	@PostMapping(value = "/{id}/class/{code}")
	 public ResponseEntity<Student> assignStudentToClassroom(@PathVariable int id,
           @PathVariable String code) throws StudentNotFoundException,ClassroomNotFoundException {
		Optional<Student> st=studentRepository.findById(id);
		Optional<Classroom> cl=classroomRepository.findById(code);
		if(!st.isPresent())   throw new StudentNotFoundException(id);
		if(!cl.isPresent())   throw new ClassroomNotFoundException(code);
		st.get().addClassroom(cl.get());
		studentRepository.save(st.get());
		return new ResponseEntity<Student>(st.get(),HttpStatus.OK);
	 }
	
	@DeleteMapping(value = "/{id}/class/{code}")
	 public ResponseEntity<Student> removeStudentFromClassroom(@PathVariable int id,
            @PathVariable String code) throws StudentNotFoundException,ClassroomNotFoundException {
		Optional<Student> st=studentRepository.findById(id);
		Optional<Classroom> cl=classroomRepository.findById(code);
		if(!st.isPresent())   throw new StudentNotFoundException(id);
		if(!cl.isPresent())   throw new ClassroomNotFoundException(code);
		st.get().removeClassroom(cl.get());
		studentRepository.save(st.get());
		return new ResponseEntity<Student>(st.get(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	private Optional<Student> getStudents(@PathVariable Integer id) throws StudentNotFoundException{
		Optional<Student> st=studentRepository.findById(id);
		if(!studentRepository.findById(id).isPresent())   throw new StudentNotFoundException(id);
		
	    return st;
	}

	@PostMapping()
	private ResponseEntity<Object> addStudent(@RequestBody Student student){
		Student st= studentRepository.save(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		        .buildAndExpand(st.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Student> updateStudent(@PathVariable Integer id,@RequestBody Student student) throws StudentNotFoundException{
		if(!studentRepository.findById(id).isPresent())  throw new StudentNotFoundException(id);
		student.setId(id);
		Student st=studentRepository.save(student);
		return new ResponseEntity<Student>(st, HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	private void deleteStudent(@PathVariable Integer id) throws StudentNotFoundException{
		if(!studentRepository.findById(id).isPresent())  throw new StudentNotFoundException(id);
		else   studentRepository.deleteById(id);
	}
}
