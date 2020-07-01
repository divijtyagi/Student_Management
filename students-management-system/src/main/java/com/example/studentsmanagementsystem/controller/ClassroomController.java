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
import com.example.studentsmanagementsystem.model.Classroom;
import com.example.studentsmanagementsystem.model.Student;
import com.example.studentsmanagementsystem.repository.ClassroomRepository;

@RestController
@RequestMapping("/classes")
public class ClassroomController {
	
//	{
//        "code": "BT1002",
//        "title": "cse-a",
//        "description": "computer science"
//    }
	@Autowired
	private ClassroomRepository classroomRepository;
	
	@GetMapping()
	private List<Classroom> getClassroom(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "description", required = false) String description) {
		if(title!=null && description!=null)   return classroomRepository.findClassroomsByTitleAndAndDescription(title, description);
		else if(title!=null)   return classroomRepository.findClassRoomsByTitleContainsIgnoreCase(title);
		else if(description!=null)   return classroomRepository.findClassroomsByDescriptionContainsIgnoreCase(description);
		return   classroomRepository.findAll();
	}
	
	@GetMapping("/{code}") 
	private Optional<Classroom> getClassroomByCode(@PathVariable String code) throws ClassroomNotFoundException {
		if(!classroomRepository.findById(code).isPresent())
			throw new ClassroomNotFoundException(code);
		return classroomRepository.findById(code);
	}
	
	@GetMapping("/{code}/students") 
	private List<Student> getStudentFromClassroom(@PathVariable String code) throws ClassroomNotFoundException {
		if(!classroomRepository.findById(code).isPresent())
			throw new ClassroomNotFoundException(code);
		Optional<Classroom> c=classroomRepository.findById(code);
		return c.get().getStudents();
		}
	
	@PostMapping()
	private ResponseEntity<Object> addClassroom(@RequestBody Classroom classroom) {
		Classroom cl = classroomRepository.save(classroom);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		        .buildAndExpand(cl.getCode()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{code}")
	private ResponseEntity<Classroom> updateClassroom(@PathVariable String code,@RequestBody Classroom classroom) {
		classroom.setCode(code);
		Classroom cl=classroomRepository.save(classroom);
		return new ResponseEntity<Classroom>(cl, HttpStatus.OK);
	}
	
	@DeleteMapping("/{code}")
	private void deleteClassroom(@PathVariable String code) throws ClassroomNotFoundException {
		if(!classroomRepository.findById(code).isPresent())
			throw new ClassroomNotFoundException(code);
		classroomRepository.deleteById(code);
		}
}
