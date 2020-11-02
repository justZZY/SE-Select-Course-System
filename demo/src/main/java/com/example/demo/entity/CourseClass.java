package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table
public class CourseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private Integer userID;
    @Column
    private Integer courseArrangementID;
    @Column
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getCourseArrangementID() {
        return courseArrangementID;
    }

    public void setCourseArrangementID(Integer courseArrangementID) {
        this.courseArrangementID = courseArrangementID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
