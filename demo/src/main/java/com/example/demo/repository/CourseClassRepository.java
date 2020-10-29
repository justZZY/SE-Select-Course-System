package com.example.demo.repository;

import com.example.demo.entity.CourseClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseClassRepository extends CrudRepository<CourseClass, Integer> {
    @Query(nativeQuery=true, value="select u.name, cc.score from user u, course_class cc, course_arrangement ca, course c where u,name=?1 and u.id=ca.teacher_id and c.name=?2 and c.id=cc.id and ca.id=c.id and ca.semester=?3")
    List<Object[]> findScoreByTeacherCourseSemester(String teacherName, String courseName, String semester);
}
