package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table
public class Course {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer credit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
