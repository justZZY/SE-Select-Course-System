package com.example.demo.repository;

import com.example.demo.entity.CourseClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseClassRepository extends CrudRepository<CourseClass, Integer> {
    @Query("select cc.id, c.name, u.name, cc.score from Course c, CourseArrangement ca, CourseClass cc, User u where ca.id=cc.courseArrangementID and c.id=ca.courseID and ca.teacherID=u.id and cc.userID=:userID")
    List<Object[]>getSelectCourse(@Param(value = "userID") int userID);
}
