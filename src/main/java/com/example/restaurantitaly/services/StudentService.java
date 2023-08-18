package com.example.restaurantitaly.services;

import com.example.restaurantitaly.entities.Course;
import com.example.restaurantitaly.entities.Group;
import com.example.restaurantitaly.models.courses.CourseSearchModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.models.students.StudentsViewModel;

import java.util.List;

public interface StudentService {

    List<StudentModel> getAllStudents();

    List<StudentsViewModel> findAllStudents();

    void addStudent(StudentModel student);

    List<StudentModel> getStudentsByName(String name);

    void deleteStudentBy(Long id);

    StudentModel getStudentById(Long id);

    StudentModel getStudentByEmail(String email);

    Long getStudentsCount();

    void removeStudent(Long id);

    List<String> getAllCourseInCurrentStudent(Long id);

    List<StudentModel> findAllStudentsByCourse(Course courseById);

    List<StudentModel> findAllStudentsByGroup(Group courseById);

    List<StudentsViewModel> getStudentByCourseAndAge(Course courseById, int age);

}
