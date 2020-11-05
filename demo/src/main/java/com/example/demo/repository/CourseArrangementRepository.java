package com.example.demo.repository;

import com.example.demo.entity.CourseArrangement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseArrangementRepository extends CrudRepository<CourseArrangement, Integer> {
    @Query("select ca.id, c.name, u.name, ca.semester, ca.time from Course c, User u, CourseArrangement ca where ca.courseID=c.id and ca.teacherID=u.id")
    List<Object[]> findAllCourseArrangement();
}
