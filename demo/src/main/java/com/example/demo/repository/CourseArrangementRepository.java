package com.example.demo.repository;

import com.example.demo.entity.CourseArrangement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseArrangementRepository extends CrudRepository<CourseArrangement, Integer> {
//    @Query(nativeQuery=true, value="select u.name, c.name, time, semester from user u, course c, course_arrangement ca where ca.course_id=c.id, ca.teacher_id=u.id")
//    List<Object[]> findAllByData();

    @Query(nativeQuery=true, value="select u.name as uname, c.name as cname, ca.time, ca.semester from user u, course c, course_arrangement ca where c.id=ca.course_id and u.id=ca.teacher_id")
    List<Object[]> findAllCourse();
}
