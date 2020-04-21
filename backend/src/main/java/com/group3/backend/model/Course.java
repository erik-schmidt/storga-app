package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.*;

@Entity
// TODO: 15.04.2020 Course im Student nur als String und Note speichern?  
public class Course {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private String room;
    private String professor;
    @Min(1)
    @Max(50)
    private int ects;
    private String fieldOfStudy;
    @Nullable
    private double grade;
    //@JsonIgnore
    //@ManyToOne(fetch = FetchType.LAZY,  cascade=CascadeType.ALL, optional =  false)
    //@JoinColumn(name = "student_id", nullable = false)

    //@JsonIgnore
    //@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
   // }, optional = false)
    //@JoinColumn(name = "student_id", nullable = false)
    //private Student student;

    public Course() {
    }

    public Course(String description, String room, String professor, int ects, String fieldOfStudy, double grade){
        this.description = description;
        this.room = room;
        this.professor = professor;
        this.ects = ects;
        this.fieldOfStudy = fieldOfStudy;
        this.grade = grade;
        //this.student = student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    /*public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }*/

    /*public void setStudent(List<Student> student) {
        this.student = student;
    }

    public List<Student> getStudent() {
        return student;
    }*/
}
