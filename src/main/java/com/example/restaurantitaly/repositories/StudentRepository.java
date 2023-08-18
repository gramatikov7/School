package com.example.restaurantitaly.repositories;

import com.example.restaurantitaly.entities.Course;
import com.example.restaurantitaly.entities.Group;
import com.example.restaurantitaly.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

    List<Student> getAllByFirstName(String name);

    Student getStudentByEmail(String email);

    @Query("SELECT c.courseType FROM Student s INNER JOIN Course c ON s.id = :studentId")
    List<String> getAllStudentsByCourse(@Param("studentId") Long studentId);

    List<Student> findAllByCourses(Course course);

    void deleteStudentByGroups(Group group);

    List<Student> findAllByCoursesAndAgeAfter(Course course , int age);

    List<Student> findAllByGroups(Group group);


}
