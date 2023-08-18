package com.example.restaurantitaly.repositories;

import com.example.restaurantitaly.entities.Group;
import com.example.restaurantitaly.entities.Student;
import com.example.restaurantitaly.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findGroupByName(String name);


    @Query("SELECT g.name FROM Group g INNER JOIN Student s ON s.id = :studentId")
    List<String> findAllByStudentId(@Param("studentId") Long studentId);

    List<Group> findAllByStudent(Student student);

    List<Group> findAllByStudentAndTeacher(Student student , Teacher teacher);

    List<Group> findAllByTeacher(Teacher teacher);
}
