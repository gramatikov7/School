package com.example.restaurantitaly.repositories;

import com.example.restaurantitaly.entities.Group;
import com.example.restaurantitaly.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher , Long> {

    Teacher findTeacherByEmail(String email);

    List<Teacher> findTeacherByGroup(String group);

//    @Modifying
//    @Query("UPDATE Teacher AS t SET t.group = :teacherGroup WHERE t.id = :id")
//    int updateTeacherGroup(@Param("group") Group teacherGroup, @Param("id") Long id);



}
