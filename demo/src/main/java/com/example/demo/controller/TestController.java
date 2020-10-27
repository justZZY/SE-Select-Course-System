package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@SpringBootApplication
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public JSONObject hello(@RequestParam(value = "test") String test) {
        JSONObject json = new JSONObject();
        json.put("name", test);
        return json;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody JSONObject userInfo) {
        int id = userInfo.getInteger("id");
        String pwd = userInfo.getString("pwd");
        User user = userRepository.findById(id).orElse(null);
        JSONObject ans = new JSONObject();
        String name = "";
        String status = "";
        if (user != null) {
            if (user.getPwd().equals(pwd)) {
                status = "success";
                name = user.getName();
            } else {
                status = "wrong";
            }
        } else {
            status = "null";
        }
        ans.put("status", status);
        ans.put("name", name);
        return ans;
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public boolean addCourse(@RequestBody JSONObject classInfo) {
        int id = classInfo.getInteger("id");
        String name = classInfo.getString("name");
        String teacher = classInfo.getString("teacher");
        int credit = classInfo.getInteger("credit");
        String time = classInfo.getString("time");
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setTeacher(teacher);
        course.setCredit(credit);
        course.setTime(time);
        courseRepository.save(course);
        return true;
    }

    @RequestMapping(value = "/getCourses", method = RequestMethod.GET)
    public JSONArray selectCourse() {
        JSONArray jsonArray = new JSONArray();
        for (Course course : courseRepository.findAll()) {
            JSONObject json = new JSONObject();
            json.put("id", course.getId());
            json.put("name", course.getName());
            json.put("teacher", course.getTeacher());
            json.put("credit", course.getCredit());
            json.put("time", course.getTime());
            jsonArray.add(json);
        }
        return jsonArray;
    }

    @RequestMapping(value = "/showDetails", method = RequestMethod.GET)
    public JSONObject showDetails(@RequestParam(value = "id") int id) {
        return null;
    }
}
