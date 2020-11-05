package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.CourseClass;
import com.example.demo.repository.CourseClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowCredentials = "true")
@RestController
@SpringBootApplication
@RequestMapping(value = "/select")
public class SelectController {
    @Autowired
    private CourseClassRepository courseClassRepository;

    @RequestMapping(value = "/selectCourse", method = RequestMethod.POST)
    public CourseClass selectCourse(@RequestBody JSONObject selectInfo) {
        int userID = selectInfo.getInteger("userID");
        int caID = selectInfo.getInteger("caID");
        int score = 0;
        CourseClass courseClass = new CourseClass();
        courseClass.setUserID(userID);
        courseClass.setCourseArrangementID(caID);
        courseClass.setScore(score);
        return courseClassRepository.save(courseClass);
    }

    @RequestMapping(value = "/deleteSelectCourse", method = RequestMethod.POST)
    public String deleteSelectCourse(@RequestBody JSONObject deleteInfo) {
        int ccID = deleteInfo.getInteger("ccID");
        CourseClass cc = new CourseClass();
        cc.setId(ccID);
        courseClassRepository.delete(cc);
        return "success";
    }

    @RequestMapping(value = "/getSelectCourse", method = RequestMethod.GET)
    public JSONArray getSelectCourse(@RequestParam(value = "userID") int userID) {
        List<Object[]> objects = courseClassRepository.getSelectCourse(userID);
        JSONArray ans = new JSONArray();
        for (Object[] o: objects) {
            JSONObject temp = new JSONObject();
            temp.put("id", o[0]);
            temp.put("courseName", o[1]);
            temp.put("teacherName", o[2]);
            temp.put("score", o[3]);
            ans.add(temp);
        }
        return ans;
    }
}
