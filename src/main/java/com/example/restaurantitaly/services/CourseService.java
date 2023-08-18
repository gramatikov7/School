package com.example.restaurantitaly.services;

import com.example.restaurantitaly.entities.Course;
import com.example.restaurantitaly.entities.Student;
import com.example.restaurantitaly.models.CourseBindingModel;
import com.example.restaurantitaly.models.CourseModel;
import com.example.restaurantitaly.models.CourseServiceModel;
import com.example.restaurantitaly.models.StudentModel;
import com.example.restaurantitaly.util.CourseType;

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
}
