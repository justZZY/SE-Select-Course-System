package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseArrangement;
import com.example.demo.entity.CourseClass;
import com.example.demo.repository.CourseArrangementRepository;
import com.example.demo.repository.CourseClassRepository;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowCredentials = "true")
@RestController
@SpringBootApplication
@RequestMapping(value = "/class")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseArrangementRepository courseArrangementRepository;

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public Course addCourse(@RequestBody JSONObject classInfo) {
        int id = classInfo.getInteger("id");
        String name = classInfo.getString("name");
        int credit = classInfo.getInteger("credit");
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setCredit(credit);
        return courseRepository.save(course);
    }

    @RequestMapping(value = "/addCourseArrangement", method = RequestMethod.POST)
    public CourseArrangement addCourseArrangement(@RequestBody JSONObject caInfo) {
        int courseID = caInfo.getInteger("courseID");
        int teacherID = caInfo.getInteger("teacherID");
        String semester = caInfo.getString("semester");
        String time = caInfo.getString("time");
        CourseArrangement ca = new CourseArrangement();
        ca.setCourseID(courseID);
        ca.setTeacherID(teacherID);
        ca.setSemester(semester);
        ca.setTime(time);
        return courseArrangementRepository.save(ca);
    }

    @RequestMapping(value = "/getAllCourse", method = RequestMethod.GET)
    public JSONArray getAllCourse() {
        Iterable<Course> courseIterable = courseRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        for (Course c: courseIterable) {
            JSONObject temp = new JSONObject();
            temp.put("id", c.getId());
            temp.put("name", c.getName());
            temp.put("credit", c.getCredit());
            jsonArray.add(temp);
        }
        return jsonArray;
    }

    @RequestMapping(value = "/getAllCourseArrangement", method = RequestMethod.GET)
    public JSONArray getAllCourseArrangement() {
        List<Object[]> courseArrangements = courseArrangementRepository.findAllCourseArrangement();
        JSONArray jsonArray = new JSONArray();
        for (Object[] o: courseArrangements) {
            JSONObject temp = new JSONObject();
            temp.put("id", o[0].toString());
            temp.put("courseName", o[1].toString());
            temp.put("teacherName", o[2].toString());
            temp.put("semester", o[3].toString());
            temp.put("time", o[4].toString());
            jsonArray.add(temp);
        }
        return jsonArray;
    }

    @RequestMapping(value = "/addUpdateCA", method = RequestMethod.POST)
    public CourseArrangement addUpdateCA(@RequestBody JSONObject courseArrangement) {
        CourseArrangement ca = getCourseArrangement(courseArrangement);
        return courseArrangementRepository.save(ca);
    }

    @RequestMapping(value = "/deleteCA", method = RequestMethod.GET)
    public String deleteCA(@RequestBody JSONObject courseArrangement) {
        CourseArrangement ca = new CourseArrangement();
        ca.setID(courseArrangement.getInteger("id"));
        courseArrangementRepository.delete(ca);
        return "success";
    }

    private CourseArrangement getCourseArrangement(@RequestBody JSONObject courseArrangement) {
        CourseArrangement ca = new CourseArrangement();
        ca.setID(courseArrangement.getInteger("id"));
        ca.setCourseID(courseArrangement.getInteger("courseID"));
        ca.setTeacherID(courseArrangement.getInteger("teacherID"));
        ca.setSemester(courseArrangement.getString("semester"));
        ca.setTime(courseArrangement.getString("time"));
        return ca;
    }
}
