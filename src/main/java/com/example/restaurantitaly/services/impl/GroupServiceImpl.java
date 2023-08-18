package com.example.restaurantitaly.services.impl;

import com.example.restaurantitaly.entities.Group;
import com.example.restaurantitaly.entities.Student;
import com.example.restaurantitaly.entities.Teacher;
import com.example.restaurantitaly.models.groups.GroupModel;
import com.example.restaurantitaly.models.groups.GroupServiceModel;
import com.example.restaurantitaly.models.groups.GroupViewModel;
import com.example.restaurantitaly.models.groups.GruopBindingModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.models.teachers.TeacherModel;
import com.example.restaurantitaly.models.teachers.TeacherServiceModel;
import com.example.restaurantitaly.models.teachers.TeacherViewModel;
import com.example.restaurantitaly.repositories.GroupRepository;
import com.example.restaurantitaly.services.GroupService;
import com.example.restaurantitaly.services.StudentService;
import com.example.restaurantitaly.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public GroupServiceImpl(GroupRepository groupRepository, TeacherService teacherService, StudentService studentService, ModelMapper modelMapper) {
        this.groupRepository = groupRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GroupModel> getAllGroups() {

        return groupRepository.findAll().stream().map(a -> modelMapper.map(a , GroupModel.class)).collect(Collectors.toList());

    }

    @Override
    public List<GroupViewModel> findAllGroups() {

        return groupRepository.findAll().stream().map(a -> modelMapper.map(a , GroupViewModel.class)).collect(Collectors.toList());

    }

    @Override
    public void addGroup(GroupModel group) {
        TeacherModel teacherByEmail = teacherService.getTeacherByEmail(group.getEmail());

        GroupServiceModel map = modelMapper.map(this.getGroupByName(group.getName()), GroupServiceModel.class);

        map.setTeacher(modelMapper.map(teacherByEmail , TeacherServiceModel.class));

        groupRepository.save(modelMapper.map(map , Group.class));


    }

    @Override
    public void createGroup(GruopBindingModel group) {

        groupRepository.saveAndFlush(modelMapper.map(group , Group.class));
    }

    @Override
    public GroupModel getGroupByName(String name) {
      return modelMapper.map(groupRepository.findGroupByName(name) , GroupModel.class);
    }

    @Override
    public void updateTeacherGroup(Teacher teacher, Long id) {

//        groupRepository.updateTeacherGroup(teacher , id);

    }

    @Override
    public void addGroupToStudent(GroupModel groupModel) {

        StudentModel studentById = studentService.getStudentById(groupModel.getId());

        GroupServiceModel groupByName = findGroupByName(groupModel.getName());

        Set<StudentModel> currentStudents = groupByName.getStudent();
        currentStudents.add(studentById);

        groupByName.setStudent(currentStudents);

        groupRepository.save(modelMapper.map(groupByName , Group.class));


//        StudentModel studentById = studentService.getStudentById(groupModel.getId());
//
//        GroupServiceModel groupServiceModel = modelMapper.map(this.getGroupByName(groupModel.getName()), GroupServiceModel.class);
//
//        groupServiceModel.setStudent(Set.of(studentById));
//
//        groupRepository.save(modelMapper.map(groupServiceModel , Group.class));


    }

    private GroupServiceModel findGroupByName(String name) {
        Group group = groupRepository.findGroupByName(name);
        return modelMapper.map(group , GroupServiceModel.class);

    }

    @Override
    public void removeGroup(Long id) {
        Group group = groupRepository.findById(id).get();

        groupRepository.delete(group);
    }

    @Override
    public List<String> getAllGroupsByStudentId(Long id) {
       return groupRepository.findAllByStudentId(id);

       //----------------------------------------
    }

    @Override
    public List<GroupServiceModel> findGroupByStudent(StudentModel studentById) {
        List<Group> allByStudent = groupRepository.findAllByStudent(modelMapper.map(studentById, Student.class));

        return allByStudent.stream().map(a -> modelMapper.map(a, GroupServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public Group getGroupById(Long id) {
       return groupRepository.findById(id).get();

    }

    @Override
    public List<GroupModel> findGroupByTeacher(TeacherViewModel teachersById) {

        List<Group> allByTeacher = groupRepository.findAllByTeacher(modelMapper.map(teachersById, Teacher.class));

        return allByTeacher.stream().map(a -> modelMapper.map(a , GroupModel.class)).collect(Collectors.toList());


    }

}
