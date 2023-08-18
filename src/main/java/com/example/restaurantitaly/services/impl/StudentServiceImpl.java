package com.example.restaurantitaly.services.impl;

import com.example.restaurantitaly.entities.Course;
import com.example.restaurantitaly.entities.Group;
import com.example.restaurantitaly.entities.Student;
import com.example.restaurantitaly.models.StudentModel;
import com.example.restaurantitaly.models.StudentsViewModel;
import com.example.restaurantitaly.repositories.StudentRepository;
import com.example.restaurantitaly.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<StudentModel> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        List<StudentModel> collect = allStudents.stream().map(a -> modelMapper.map(a, StudentModel.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<StudentsViewModel> findAllStudents() {
        List<Student> all = studentRepository.findAll();
        return all.stream().map(a-> modelMapper.map(a ,StudentsViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void addStudent(StudentModel studentModel) {
        studentRepository.saveAndFlush(modelMapper.map(studentModel, Student.class));
    }

    @Override
    public List<StudentModel> getStudentsByName(String name) {
        List<Student> allByFirstName = studentRepository.getAllByFirstName(name);
        List<StudentModel> collect = allByFirstName.stream().map(a -> modelMapper.map(a, StudentModel.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteStudentBy(Long id) {

    }

    @Override
    public StudentModel getStudentById(Long id) {
        return modelMapper.map(studentRepository.getById(id), StudentModel.class);
    }

    @Override
    public StudentModel getStudentByEmail(String email) {

        StudentModel studentModel = modelMapper.map(studentRepository.getStudentByEmail(email), StudentModel.class);
        return studentModel;
    }

    @Override
    public Long getStudentsCount() {

       return studentRepository.count();

    }

    @Override
    public void removeStudent(Long id) {
        Student student = studentRepository.getById(id);

        studentRepository.delete(student);
    }

    @Override
    public List<String> getAllCourseInCurrentStudent(Long id) {

      return studentRepository.getAllStudentsByCourse(id);

    }

    @Override
    public List<StudentModel> findAllStudentsByCourse(Course courseById) {
        List<Student> allByCourses = studentRepository.findAllByCourses(courseById);
        return allByCourses.stream().map(a -> modelMapper.map(a , StudentModel.class)).collect(Collectors.toList());


    }

    @Override
    public List<StudentModel> findAllStudentsByGroup(Group group) {

        List<Student> allByGroups = studentRepository.findAllByGroups(group);
        return allByGroups.stream().map(a -> modelMapper.map(a , StudentModel.class)).collect(Collectors.toList());


    }
}
