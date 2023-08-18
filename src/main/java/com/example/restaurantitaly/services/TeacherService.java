package com.example.restaurantitaly.services;

import com.example.restaurantitaly.models.teachers.TeacherModel;
import com.example.restaurantitaly.models.teachers.TeacherServiceModel;
import com.example.restaurantitaly.models.teachers.TeacherViewModel;

import java.util.List;

public interface TeacherService {

    void addTeacher(TeacherServiceModel teacher);

    List<TeacherModel> getAllTeachers();

    TeacherModel getTeacherByEmail(String email);

    void addGroupToTeacher(TeacherModel teacherServiceModel);

    List<TeacherModel> getAllTeachersByGroup(String groupName);

    Long getTeacherCount();

    List<TeacherViewModel> findAllTeachers();

    void removeTeacher(Long id);

    TeacherViewModel getTeachersById(Long id);
}
