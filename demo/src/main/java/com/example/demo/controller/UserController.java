package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(allowCredentials = "true")
@RestController
@SpringBootApplication
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/getAllTeacher", method = RequestMethod.GET)
    public JSONArray getAllTeacher() {
        List<User> users = userRepository.findByRoles_Name("teacher");
        JSONArray jsonArray = new JSONArray();
        for (User u: users) {
            JSONObject temp = new JSONObject();
            temp.put("id", u.getId());
            temp.put("name", u.getName());
            jsonArray.add(temp);
        }
        return jsonArray;
    }
}
