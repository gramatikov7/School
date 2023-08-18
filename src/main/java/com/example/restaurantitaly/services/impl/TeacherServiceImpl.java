package com.example.restaurantitaly.services.impl;

import com.example.restaurantitaly.entities.Teacher;
import com.example.restaurantitaly.models.teachers.TeacherModel;
import com.example.restaurantitaly.models.teachers.TeacherServiceModel;
import com.example.restaurantitaly.models.teachers.TeacherViewModel;
import com.example.restaurantitaly.repositories.TeacherRepository;
import com.example.restaurantitaly.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
//    private final GroupService groupService;
    private final ModelMapper modelMapper;

    public TeacherServiceImpl( TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
//        this.groupService = groupService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addTeacher(TeacherServiceModel teacherModel) {
        Teacher teacher = modelMapper.map(teacherModel, Teacher.class);
        teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public List<TeacherModel> getAllTeachers() {
        List<TeacherModel> collect = teacherRepository.findAll().stream().map(a -> modelMapper.map(a, TeacherModel.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public TeacherModel getTeacherByEmail(String email) {

        Teacher teacherByEmail = teacherRepository.findTeacherByEmail(email);

        return modelMapper.map(teacherByEmail, TeacherModel.class);

    }

    @Override
    public void addGroupToTeacher(TeacherModel teacherModel) {

//        TeacherModel teacherByEmail = this.getTeacherByEmail(teacherModel.getEmail());
//        GroupModel groupByName = groupService.getGroupByName(teacherModel.getGroup());
//
//        TeacherServiceModel teacherServiceModel = modelMapper.map(teacherByEmail, TeacherServiceModel.class);
//        teacherServiceModel.setGroup(List.of(groupByName));
//
//        Teacher newTeacher = modelMapper.map(teacherServiceModel, Teacher.class);
//        teacherRepository.saveAndFlush(newTeacher);

//        teacherRepository.updateTeacherGroup(group , id);
    }

    @Override
    public List<TeacherModel> getAllTeachersByGroup(String groupName) {

        List<Teacher> teacherByGroup = teacherRepository.findTeacherByGroup(groupName);
        return teacherByGroup.stream().map(a -> modelMapper.map(a , TeacherModel.class)).collect(Collectors.toList());
    }

    @Override
    public Long getTeacherCount() {
        return teacherRepository.count();
    }

    @Override
    public List<TeacherViewModel> findAllTeachers() {
        return teacherRepository.findAll().stream().map(a -> modelMapper.map(a, TeacherViewModel.class)).collect(Collectors.toList());

    }

    @Override
    public void removeTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();

        teacherRepository.delete(teacher);
    }

    @Override
    public TeacherViewModel getTeachersById(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();
        return modelMapper.map(teacher , TeacherViewModel.class);
    }


}
