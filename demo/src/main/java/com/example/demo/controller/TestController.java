package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@SpringBootApplication
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public JSONObject hello(@RequestParam(value = "test") String test) {
        JSONObject json = new JSONObject();
        json.put("name", test);
        return json;
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public boolean addCourse(@RequestBody JSONObject classInfo) {
        return false;
    }

    @RequestMapping(value = "/selectCourse", method = RequestMethod.POST)
    public boolean selectCourse(@RequestBody JSONObject classInfo) {
        return false;
    }

    @RequestMapping(value = "/showDetails", method = RequestMethod.GET)
    public JSONObject showDetails(@RequestParam(value = "id") int id) {
        return null;
    }
}
