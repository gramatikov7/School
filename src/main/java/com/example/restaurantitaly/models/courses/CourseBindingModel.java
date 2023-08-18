package com.example.restaurantitaly.models.courses;

import com.example.restaurantitaly.models.teachers.TeacherServiceModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.util.CourseType;

import java.util.Set;

public class CourseBindingModel {
    private Long id;
    private CourseType courseType;
    private int age;
    private TeacherServiceModel teacher;
    private Set<StudentModel> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
