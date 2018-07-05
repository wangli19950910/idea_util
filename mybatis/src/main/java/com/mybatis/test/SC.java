package com.mybatis.test;

import java.io.Serializable;

public class SC implements Serializable{

    private int student_id;
    private int course_id;
    private String course_name;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    @Override
    public String toString() {
        return "SC{" +
                "student_id=" + student_id +
                ", course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                '}';
    }
}
