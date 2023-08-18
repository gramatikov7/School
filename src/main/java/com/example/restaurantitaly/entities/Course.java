package com.example.restaurantitaly.entities;


import com.example.restaurantitaly.util.CourseType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    private Teacher teacher;
    private Set<Student> students;
    private CourseType courseType;


    @Column(nullable = false )
    @Enumerated(EnumType.STRING )
    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

//    @JoinTable(
//            name = "courses_student",
//            joinColumns = @JoinColumn(name = "course_id" , referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id" , referencedColumnName = "id"))

    @ManyToMany(targetEntity = Student.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"))
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
