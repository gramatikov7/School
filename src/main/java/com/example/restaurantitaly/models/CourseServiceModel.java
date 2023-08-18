package com.example.restaurantitaly.models;

import com.example.restaurantitaly.util.CourseType;

public class CourseServiceModel {

    private Long id;
    private CourseType courseType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
