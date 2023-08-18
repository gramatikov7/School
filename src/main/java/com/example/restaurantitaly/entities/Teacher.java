package com.example.restaurantitaly.entities;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
@DynamicUpdate
public class Teacher extends BaseEntity {

    private String firstName;
    private String secondName;
    private int age;
    private String email;
    private Set<Group> group;
    private Set<Course> course;

    @Column(name = "first_name" , nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "second_name" , nullable = false)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "email" , nullable = false , unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "teacher" , cascade = CascadeType.REMOVE)
    public Set<Group> getGroup() {
        return group;
    }

    public void setGroup(Set<Group> group) {
        this.group = group;
    }

    @OneToMany(mappedBy = "teacher" , cascade = CascadeType.REMOVE)
    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }





}
