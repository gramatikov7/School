package com.example.restaurantitaly.models;

import java.util.List;

public class TeacherServiceModel {

    private Long id;
    private String firstName;
    private String secondName;
    private int age;
    private String email;
    private List<GroupModel> group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GroupModel> getGroup() {
        return group;
    }

    public void setGroup(List<GroupModel> group) {
        this.group = group;
    }
}
