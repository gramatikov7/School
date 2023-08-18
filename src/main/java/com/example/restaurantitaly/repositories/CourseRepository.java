package com.example.restaurantitaly.repositories;

import com.example.restaurantitaly.entities.Course;
import com.example.restaurantitaly.entities.Student;
import com.example.restaurantitaly.entities.Teacher;
import com.example.restaurantitaly.util.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course , Long> {

    Course findCourseByCourseType(CourseType type);

    List<Course> getAllByCourseType(CourseType courseType);

    List<Course> findAllByStudents(Student student);

    List<Course> findAllByTeacher(Teacher teacher);

    List<Course> findAllByStudentsAndTeacher(Student student , Teacher teacher);









}
