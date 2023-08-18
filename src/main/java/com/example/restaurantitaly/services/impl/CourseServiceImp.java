package com.example.restaurantitaly.services.impl;

import com.example.restaurantitaly.entities.Course;
import com.example.restaurantitaly.entities.Student;
import com.example.restaurantitaly.entities.Teacher;
import com.example.restaurantitaly.models.courses.CourseBindingModel;
import com.example.restaurantitaly.models.courses.CourseModel;
import com.example.restaurantitaly.models.courses.CourseServiceModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.models.teachers.TeacherModel;
import com.example.restaurantitaly.models.teachers.TeacherViewModel;
import com.example.restaurantitaly.repositories.CourseRepository;
import com.example.restaurantitaly.services.CourseService;
import com.example.restaurantitaly.services.StudentService;
import com.example.restaurantitaly.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CourseServiceImp(CourseRepository courseRepository, ModelMapper modelMapper, TeacherService teacherService, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }


    @Override
    public Course getCourseById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        return byId.orElse(null);
    }


    @Override
    public List<CourseServiceModel> getAllCurses() {
        return courseRepository.findAll().stream().map(a -> modelMapper.map(a, CourseServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<CourseBindingModel> findAllCurses() {
        return courseRepository.findAll().stream().map(a -> modelMapper.map(a, CourseBindingModel.class)).collect(Collectors.toList());
    }

    @Override
    public void removeCourse(Long id) {
        Course course = courseRepository.getById(id);

        courseRepository.delete(course);
    }

    @Override
    public List<CourseServiceModel> findCourseByTeacher(TeacherViewModel teachersById) {

        List<Course> allByTeacher = courseRepository.findAllByTeacher(modelMapper.map(teachersById, Teacher.class));

        return allByTeacher.stream().map(a -> modelMapper.map(a, CourseServiceModel.class)).collect(Collectors.toList());

    }


    @Override
    public void addCourse(CourseModel courseModel) {

        TeacherModel teacherByEmail = teacherService.getTeacherByEmail(courseModel.getEmail());


        Course courseById = this.getCourseById(courseModel.getId());

        courseById.setTeacher(modelMapper.map(teacherByEmail, Teacher.class));


        courseRepository.save(courseById);


    }

    @Override
    public void createCourse(CourseModel course) {

        courseRepository.saveAndFlush(modelMapper.map(course, Course.class));

    }

    @Override
    public void addCourseToStudent(CourseModel courseModel) {

        StudentModel studentById = studentService.getStudentByEmail(courseModel.getEmail());

        CourseBindingModel courseById = findCourseById(courseModel.getId());

        Set<StudentModel> currentStudents = courseById.getStudents();
        currentStudents.add(studentById);

        courseById.setStudents(currentStudents);

        courseRepository.save(modelMapper.map(courseById, Course.class));

    }

    @Override
    public CourseBindingModel findCourseById(Long id) {
        Course course = this.courseRepository.findById(id).get();
        return modelMapper.map(course, CourseBindingModel.class);
    }

    @Override
    public List<CourseServiceModel> findCourseByStudent(StudentModel studentById) {
        List<Course> allByStudents = courseRepository.findAllByStudents(modelMapper.map(studentById, Student.class));

        return allByStudents.stream().map(a -> modelMapper.map(a, CourseServiceModel.class)).collect(Collectors.toList());
    }

}
