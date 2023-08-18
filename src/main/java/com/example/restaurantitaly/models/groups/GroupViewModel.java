package com.example.restaurantitaly.models.groups;

import com.example.restaurantitaly.entities.Student;
import com.example.restaurantitaly.entities.Teacher;

import java.util.Set;

public class GroupViewModel {
    private Long id;
    private String name;
    private Teacher teacher;
    private Set<Student> student;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

}
