package com.example.restaurantitaly.controllers;

import com.example.restaurantitaly.entities.Teacher;
import com.example.restaurantitaly.models.courses.CourseServiceModel;
import com.example.restaurantitaly.models.groups.GroupModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.models.teachers.TeacherModel;
import com.example.restaurantitaly.models.teachers.TeacherServiceModel;
import com.example.restaurantitaly.models.teachers.TeacherViewModel;
import com.example.restaurantitaly.services.CourseService;
import com.example.restaurantitaly.services.GroupService;
import com.example.restaurantitaly.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController extends BaseController {

    private final TeacherService teacherService;
    private final ModelMapper modelMapper;
    private final CourseService courseService;
    private final GroupService groupService;

    public TeacherController(TeacherService teacherService, ModelMapper modelMapper, CourseService courseService, GroupService groupService) {
        this.teacherService = teacherService;
        this.modelMapper = modelMapper;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    @GetMapping("/teachers")
    public ModelAndView teacherPage(@ModelAttribute(name = "teacher") Teacher teacher) {

        return view("teacher/teacher");
    }

    @GetMapping("/all")
    @ResponseBody
    public List<TeacherModel> allTeachers(@ModelAttribute(name = "teacher") TeacherModel teacherModel, ModelAndView modelAndView) {

        return teacherService.getAllTeachers();

    }

    @PostMapping("/add")
    public ModelAndView addTeacher(@ModelAttribute(name = "teacher") @Valid TeacherModel teacherModel,
                                    BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return view("students/students");

        }

        teacherService.addTeacher(modelMapper.map(teacherModel , TeacherServiceModel.class));

        return redirect("/teachers/teachers");
    }

    @PostMapping("/group")
    public ModelAndView addGroupToTeacher(@ModelAttribute(name = "teacher") @Valid TeacherModel teacherModel,
                                   BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return view("teachers/teachers");

        }


        List<TeacherModel> allTeachersByGroup = teacherService.getAllTeachersByGroup(teacherModel.getGroup());
        teacherService.addGroupToTeacher(teacherModel);

        return redirect("/teachers/teachers");
    }

    @GetMapping("/count")
    @ResponseBody
    public Long teacherCount(@ModelAttribute(name = "teacher") StudentModel studentModel){

        return  teacherService.getTeacherCount();

    }

    @GetMapping("/query")
    public ModelAndView teacherQuery(ModelAndView modelAndView){

        List<TeacherViewModel> allTeachers = teacherService.findAllTeachers();

        modelAndView.addObject("teacher", allTeachers);


        return view("teacher/tquery", modelAndView);

    }

    @GetMapping("/remove/{id}")
    public ModelAndView deleteTeacher(@PathVariable Long id){

        teacherService.removeTeacher(id);


        return redirect("/teachers/query");

    }


    @GetMapping("/courses/{id}")
    public ModelAndView teacherCourses(@PathVariable Long id, ModelAndView modelAndView) {

        TeacherViewModel teachersById = teacherService.getTeachersById(id);

        List<CourseServiceModel> courseByTeacher = courseService.findCourseByTeacher(teachersById);


        modelAndView.addObject("courses", courseByTeacher);

        return view("teacher/tquery", modelAndView);

    }

    @GetMapping("/groups/{id}")
    public ModelAndView teacherGroups(@PathVariable Long id, ModelAndView modelAndView) {

        TeacherViewModel teachersById = teacherService.getTeachersById(id);


        List<GroupModel> groupByType = groupService.findGroupByTeacher(teachersById);


        modelAndView.addObject("groups", groupByType);

        return view("teacher/tquery", modelAndView);

    }

}
