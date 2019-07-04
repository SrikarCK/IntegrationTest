package com.integrationtest.springboot.model;

import java.util.List;

public class Course {

    private String course_Id;
    private String course_Name;
    private String course_Description;
    private List<String> course_Steps;


    public Course() {

    }

    public Course(String course_Id, String course_Name, String course_Discription, List<String> course_Steps) {
        super();
        this.course_Id = course_Id;
        this.course_Name = course_Name;
        this.course_Description = course_Discription;
        this.course_Steps = course_Steps;
    }

    public String getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(String course_Id) {
        this.course_Id = course_Id;
    }

    public String getCourse_Name() {
        return course_Name;
    }

    public String getCourse_Discription() {
        return course_Description;
    }

    public List<String> getCourse_Steps() {
        return course_Steps;
    }

    @Override
    public String toString() {
        return String.format("Course [id=%s, name=%s, description=%s, steps=%s]", course_Id, course_Name,
                course_Description, course_Steps);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course_Id == null) ? 0 : course_Id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (course_Id == null) {
            if (other.course_Id != null)
                return false;
        } else if (!course_Id.equals(other.course_Id))
            return false;
        return true;
    }


}
