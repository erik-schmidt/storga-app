package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class GradeCourseMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String courseNumber;
    @Min(1)
    @Max(5)
    private double grade;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    public GradeCourseMapping() {
    }

    public GradeCourseMapping(String courseNumber, double grade) {
        this.courseNumber = courseNumber;
        this.grade = grade;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(courseNumber + " ");
        sb.append(grade);
        return sb.toString();
    }
}