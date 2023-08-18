package com.example.restaurantitaly.controllers;

import com.example.restaurantitaly.entities.Course;
import com.example.restaurantitaly.models.courses.CourseBindingModel;
import com.example.restaurantitaly.models.courses.CourseModel;
import com.example.restaurantitaly.models.courses.CourseSearchModel;
import com.example.restaurantitaly.models.courses.CourseServiceModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.models.students.StudentsViewModel;
import com.example.restaurantitaly.services.CourseService;
import com.example.restaurantitaly.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("courses")
public class CoursesController extends BaseController {

    private final CourseService courseService;
    private final StudentService studentService;

    public CoursesController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping("/courses")
    public ModelAndView homePage(@ModelAttribute(name = "course") Course course) {

        return view("courses/courses");
    }

    @PostMapping("/add")
    public ModelAndView createCourse(@ModelAttribute(name = "course") CourseModel course) {

        courseService.createCourse(course);

        return view("courses/courses");
    }

    @GetMapping("/all")
    @ResponseBody
    public List<CourseServiceModel> fetchAllBookingForUser(Principal principal) {

        return courseService.getAllCurses();


    }

    @PostMapping("/teacher")
    public ModelAndView addTeacherToCourse(@ModelAttribute(name = "course") @Valid CourseModel courseModel,
                                           BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return view("courses/courses");

        }

        courseService.addCourse(courseModel);

        return redirect("/courses/courses");
    }

    @PostMapping("/student")
    public ModelAndView addStudentToCourse(@ModelAttribute(name = "courses") @Valid CourseModel courseModel,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return view("courses/courses");
        }
        courseService.addCourseToStudent(courseModel);

        return redirect("/courses/courses");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView deleteCourse(@PathVariable Long id) {

        courseService.removeCourse(id);


        return redirect("/courses/query");

    }

    @GetMapping("/query")
    public ModelAndView courseQuery(ModelAndView modelAndView) {

        List<CourseBindingModel> allCurses = courseService.findAllCurses();

        modelAndView.addObject("courses", allCurses);


        return view("courses/cquery", modelAndView);

    }

    @GetMapping("/allStudents/{id}")
    public ModelAndView studentGroups(@PathVariable Long id, ModelAndView modelAndView) {
        Course courseById = courseService.getCourseById(id);
        List<StudentModel> allStudentsByCourse = studentService.findAllStudentsByCourse(courseById);

        modelAndView.addObject("students", allStudentsByCourse);

        return view("courses/cquery", modelAndView);

    }


    @GetMapping("/search")
    public ModelAndView courseSearch(ModelAndView modelAndView) {

        List<CourseBindingModel> allCurses = courseService.findAllCurses();

        modelAndView.addObject("courses", allCurses);


        return view("courses/search", modelAndView);

    }

    @PostMapping("/search")
    public ModelAndView courseSearches(@ModelAttribute(name = "students") CourseSearchModel courseSearchModel , ModelAndView modelAndView) {

        Course courseById = courseService.getCourseById(courseSearchModel.getId());

        List<StudentsViewModel> studentByCourseAndAge = studentService.getStudentByCourseAndAge(courseById , courseSearchModel.getAge());

        modelAndView.addObject("students" , studentByCourseAndAge);

        return view("courses/search" , modelAndView);

    }


}
