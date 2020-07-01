package com.example.studentsmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.studentsmanagementsystem.model.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {
	List<Classroom> findClassRoomsByTitleContainsIgnoreCase(String title);
	List<Classroom> findClassroomsByDescriptionContainsIgnoreCase(String description);
	@Query("SELECT classroom FROM Classroom classroom WHERE LOWER(classroom.title) LIKE LOWER(:title) AND LOWER(classroom.description) LIKE LOWER(:description)")
    List<Classroom> findClassroomsByTitleAndAndDescription(@Param("title") String title, @Param("description") String description);

}
