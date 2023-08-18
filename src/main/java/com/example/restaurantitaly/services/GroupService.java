package com.example.restaurantitaly.services;

import com.example.restaurantitaly.entities.Group;
import com.example.restaurantitaly.entities.Teacher;
import com.example.restaurantitaly.models.groups.GroupModel;
import com.example.restaurantitaly.models.groups.GroupServiceModel;
import com.example.restaurantitaly.models.groups.GroupViewModel;
import com.example.restaurantitaly.models.groups.GruopBindingModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.models.teachers.TeacherViewModel;

import java.util.List;

public interface GroupService {
    List<GroupModel> getAllGroups();

    List<GroupViewModel> findAllGroups();

    void addGroup(GroupModel group);

    void createGroup(GruopBindingModel group);

    GroupModel getGroupByName(String name);

    void updateTeacherGroup(Teacher teacher , Long id);

    void addGroupToStudent(GroupModel groupModel);

    void removeGroup(Long id);

    List<String> getAllGroupsByStudentId(Long id);

    List<GroupServiceModel> findGroupByStudent(StudentModel studentById);

    Group getGroupById(Long id);

    List<GroupModel> findGroupByTeacher(TeacherViewModel teachersById);
}
