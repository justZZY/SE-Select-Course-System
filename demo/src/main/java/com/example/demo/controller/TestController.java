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

@CrossOrigin(allowCredentials = "true")
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
}
