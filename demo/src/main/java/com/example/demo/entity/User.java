package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @TableGenerator(
            name = "AppSeqStore",
            initialValue = 10000,
            allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "AppSeqStore")
    private Integer id;
    @Column
    private String name;
    @Column
    private String pwd;
    @ManyToMany
    @JoinTable
    private List<Role> roles;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
