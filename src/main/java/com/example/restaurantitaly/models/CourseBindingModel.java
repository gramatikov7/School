package com.example.restaurantitaly.models;

import com.example.restaurantitaly.util.CourseType;

import java.util.Set;

public class CourseBindingModel {
    private Long id;
    private CourseType courseType;
    private int participants;
    private TeacherServiceModel teacher;
    private Set<StudentModel> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<StudentModel> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentModel> students) {
        this.students = students;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
