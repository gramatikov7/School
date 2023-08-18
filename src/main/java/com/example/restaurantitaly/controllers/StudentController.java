package com.example.restaurantitaly.controllers;

import com.example.restaurantitaly.entities.Student;
import com.example.restaurantitaly.models.courses.CourseServiceModel;
import com.example.restaurantitaly.models.groups.GroupServiceModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.models.students.StudentsViewModel;
import com.example.restaurantitaly.services.CourseService;
import com.example.restaurantitaly.services.GroupService;
import com.example.restaurantitaly.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController extends BaseController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final GroupService groupService;
    private final ModelMapper modelMapper;

    public StudentController(StudentService studentService, CourseService courseService, GroupService groupService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.groupService = groupService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/students")
    public ModelAndView studentPage(@ModelAttribute(name = "student") Student student) {
        return view("students/students");
    }

    @GetMapping("/all")
    @ResponseBody
    public List<StudentModel> allStudents(@ModelAttribute(name = "student") Student student, ModelAndView modelAndView) {

        return studentService.getAllStudents();
    }


    @PostMapping("/add")
    public ModelAndView addCategory(@ModelAttribute(name = "student") @Valid StudentModel studentModel,
                                    BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return view("students/students");
        }

        studentService.addStudent(studentModel);
        return redirect("/students/students");

    }

    @GetMapping("/remove/{id}")
    public ModelAndView deleteStudent(@PathVariable Long id) {

        studentService.removeStudent(id);


        return redirect("/students/query");

    }

    @GetMapping("/courses/{id}")
    public ModelAndView studentCourses(@PathVariable Long id, ModelAndView modelAndView) {

        StudentModel studentById = studentService.getStudentById(id);

        List<CourseServiceModel> allCoursesByStudentId = courseService.findCourseByStudent(studentById);

        modelAndView.addObject("courses", allCoursesByStudentId);

        return view("students/query", modelAndView);

    }

    @GetMapping("/groups/{id}")
    public ModelAndView studentGroups(@PathVariable Long id, ModelAndView modelAndView) {

        StudentModel studentById = studentService.getStudentById(id);

        List<GroupServiceModel> allGroupsByStudentId = groupService.findGroupByStudent(studentById);

        modelAndView.addObject("groups", allGroupsByStudentId);

        return view("students/query", modelAndView);

    }

    @GetMapping("/count")
    @ResponseBody
    public Long studentsCount(@ModelAttribute(name = "student") StudentModel studentModel) {

        return studentService.getStudentsCount();

    }

    @GetMapping("/query")
    public ModelAndView studentsQuery(ModelAndView modelAndView) {

        List<StudentsViewModel> allStudents = studentService.findAllStudents();

        modelAndView.addObject("student", allStudents);


        return view("students/query", modelAndView);

    }


}

