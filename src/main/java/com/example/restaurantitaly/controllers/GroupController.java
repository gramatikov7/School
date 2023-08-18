package com.example.restaurantitaly.controllers;

import com.example.restaurantitaly.entities.Group;
import com.example.restaurantitaly.models.groups.GroupModel;
import com.example.restaurantitaly.models.groups.GroupViewModel;
import com.example.restaurantitaly.models.groups.GruopBindingModel;
import com.example.restaurantitaly.models.students.StudentModel;
import com.example.restaurantitaly.services.GroupService;
import com.example.restaurantitaly.services.StudentService;
import com.example.restaurantitaly.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController extends BaseController {

    private final GroupService groupService;
    private final StudentService studentService;

    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping("/groups")
    public ModelAndView studentPage(@ModelAttribute(name = "groups") GroupModel groupModel) {
        return view("groups/groups");
    }

    @GetMapping("/all")
    @ResponseBody
    public List<GroupModel> allGroups() {

        return groupService.getAllGroups();

    }

    @PostMapping("/add")
    public ModelAndView addGroup(@ModelAttribute(name = "groups") @Valid GruopBindingModel groupModel,
                                   BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return view("groups/groups");

        }

        groupService.createGroup(groupModel);

        return redirect("/groups/groups");
    }

    @PostMapping("/teacher")
    public ModelAndView addTeacherToGroup(@ModelAttribute(name = "groups") @Valid GroupModel groupModel,
                                 BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return view("groups/groups");

        }


        groupService.addGroup(groupModel);

        return redirect("/groups/groups");
    }

    @PostMapping("/student")
    public ModelAndView addStudentToGroup(@ModelAttribute(name = "groups") @Valid GroupModel groupModel,
                                          BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return view("groups/groups");

        }


        groupService.addGroupToStudent(groupModel);

        return redirect("/groups/groups");
    }



    @GetMapping("/query")
    public ModelAndView groupQuery(ModelAndView modelAndView){

        List<GroupViewModel> allGroups = groupService.findAllGroups();

        modelAndView.addObject("groups", allGroups);


        return view("groups/gquery", modelAndView);

    }

    @GetMapping("/remove/{id}")
    public ModelAndView deleteGroup(@PathVariable Long id){

        groupService.removeGroup(id);

        return redirect("/groups/query");

    }

    @GetMapping("/allStudents/{id}")
    public ModelAndView studentGroups(@PathVariable Long id, ModelAndView modelAndView) {

        Group groupById = groupService.getGroupById(id);

        List<StudentModel> allStudentsByCourse = studentService.findAllStudentsByGroup(groupById);

        modelAndView.addObject("students", allStudentsByCourse);

        return view("groups/gquery", modelAndView);

    }

}
