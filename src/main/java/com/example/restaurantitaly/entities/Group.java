package com.example.restaurantitaly.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "school_groups")
public class Group extends BaseEntity {

    private String name;
    private Teacher teacher;
    private Set<Student> student;
    private int participants;

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "participants", nullable = false)
    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    //    @ManyToMany(mappedBy = "groups")
    @ManyToMany(targetEntity = Student.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "group_student",
            joinColumns = @JoinColumn(name = "school_groups_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"))
    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
