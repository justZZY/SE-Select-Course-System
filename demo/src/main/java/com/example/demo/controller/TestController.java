package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Role;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@SpringBootApplication
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseClassRepository courseClassRepository;
    @Autowired
    private CourseArrangementRepository courseArrangementRepository;

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

    @RequestMapping(value = "/insertRole", method = RequestMethod.GET)
    public String insertRole(@RequestParam(value = "name") String name) {
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
        return role.toString();
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.GET)
    public String insertUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "pwd") String pwd, @RequestParam(value = "role") int r_id) {
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        Role role = roleRepository.findById(r_id).orElse(null);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);
        userRepository.save(user);
        return user.toString();
    }

    @RequestMapping(value = "/getAllTeacher", method = RequestMethod.GET)
    public String getAllTeacher(@RequestParam(value = "role_id") int id) {
        List<String> ans = userRepository.findByRoleID(id);
        String temp = "";
        for (String s: ans) {
            temp = temp + s + " ";
        }
        return temp;
    }

    @RequestMapping(value = "/getAllUserChooseCourse", method = RequestMethod.GET)
    public String getAllUserChooseCourse(@RequestParam(value = "course_like") String courseLike) {
        List<String> userNames = courseRepository.findUserNameByCourseNameLike(courseLike);
        String temp = "";
        for (String s: userNames) {
            temp = temp + s + " ";
        }
        return temp;
    }

    @RequestMapping(value = "/getAllCourse", method = RequestMethod.GET)
    public String getAllCourse() {
        List<Object[]> courses = courseArrangementRepository.findAllCourse();
        String temp = "";
        for (Object[] o: courses) {
            for (Object t: o) {
                temp = temp + t.toString() + " ";
            }
        }
        return temp;
    }

    @RequestMapping(value = "/getAllScore", method = RequestMethod.GET)
    public String getAllScore() {
        List<Object[]> courses = courseClassRepository.findAllScore();
        String temp = "";
        for (Object[] o: courses) {
            for (Object t: o) {
                temp = temp + t.toString() + " ";
            }
        }
        return temp;
    }
//    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
//    public boolean addCourse(@RequestBody JSONObject classInfo) {
//        int id = classInfo.getInteger("id");
//        String name = classInfo.getString("name");
//        String teacher = classInfo.getString("teacher");
//        int credit = classInfo.getInteger("credit");
//        String time = classInfo.getString("time");
//        Course course = new Course();
//        course.setId(id);
//        course.setName(name);
//        course.setTeacher(teacher);
//        course.setCredit(credit);
//        course.setTime(time);
//        courseRepository.save(course);
//        return true;
//    }

//    @RequestMapping(value = "/getCourses", method = RequestMethod.GET)
//    public JSONArray selectCourse() {
//        JSONArray jsonArray = new JSONArray();
//        for (Course course : courseRepository.findAll()) {
//            JSONObject json = new JSONObject();
//            json.put("id", course.getId());
//            json.put("name", course.getName());
//            json.put("teacher", course.getTeacher());
//            json.put("credit", course.getCredit());
//            json.put("time", course.getTime());
//            jsonArray.add(json);
//        }
//        return jsonArray;
//    }

    @RequestMapping(value = "/showDetails", method = RequestMethod.GET)
    public JSONObject showDetails(@RequestParam(value = "id") int id) {
        return null;
    }
}
