package com.example.restaurantitaly.services;

import com.example.restaurantitaly.entities.Course;
import com.example.restaurantitaly.models.courses.CourseBindingModel;
import com.example.restaurantitaly.models.courses.CourseModel;
import com.example.restaurantitaly.models.courses.CourseSearchModel;
import com.example.restaurantitaly.models.courses.CourseServiceModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.models.teachers.TeacherViewModel;

import java.util.List;

public interface CourseService {

    Course getCourseById(Long id);

    List<CourseServiceModel> getAllCurses();


    void addCourse(CourseModel course);

    void createCourse(CourseModel course);

    void addCourseToStudent(CourseModel courseModel);


    CourseBindingModel findCourseById(Long id);

    List<CourseServiceModel> findCourseByStudent(StudentModel studentById);

    List<CourseBindingModel> findAllCurses();

    void removeCourse(Long id);

    List<CourseServiceModel> findCourseByTeacher(TeacherViewModel teachersById);
}
