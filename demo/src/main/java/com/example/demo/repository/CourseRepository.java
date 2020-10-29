package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query(nativeQuery=true, value="select user.name from course c, user u, course_class cc where c.name like ?1 and cc.course_id=c.id and u.id=cc.user_id")
    List<String> findUserNameByCourseNameLike(String like);
}
