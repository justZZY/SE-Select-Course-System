package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "true")
@RestController
@SpringBootApplication
@RequestMapping(value = "/log")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

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
}
