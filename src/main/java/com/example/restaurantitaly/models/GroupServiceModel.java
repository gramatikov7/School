package com.example.restaurantitaly.models;

import java.util.List;
import java.util.Set;

public class GroupServiceModel {

    private Long id;
    private String name;
    private String email;
    private int participants;
    private TeacherServiceModel teacher;
    private Set<StudentModel> student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public TeacherServiceModel getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherServiceModel teacher) {
        this.teacher = teacher;
    }

    public Set<StudentModel> getStudent() {
        return student;
    }

    public void setStudent(Set<StudentModel> student) {
        this.student = student;
    }
}
