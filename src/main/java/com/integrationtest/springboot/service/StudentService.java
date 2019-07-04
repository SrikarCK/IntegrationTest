package com.integrationtest.springboot.service;

import com.integrationtest.springboot.model.Course;
import com.integrationtest.springboot.model.Student;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StudentService {

    private static List<Student> students = new ArrayList<>();

    static {
        Course course1 = new Course("Course1","Spring","10 Steps",
                Arrays.asList("Learn Maven","Import Project","First Example"));
        Course course2 = new Course("Course2","Spring MVC","10 Examples",
                Arrays.asList("Learn Maven","Import Project","First Example"));
        Course course3 = new Course("Course3","Spring Boot","6k Students",
                Arrays.asList("Learn Spring","Learn Spring Boot","Learn Dependencies"));
        Student Srikar = new Student("Student1","Srikar","Air Force",
                new ArrayList<>(Arrays.asList(course1,course2)));
        Student Katta = new Student("Student2", "Katta C",
                "Motocyclist, Programmer and Drinker", new ArrayList<>(Arrays
                .asList(course1, course3)));

        students.add(Srikar);
        students.add(Katta);
    }

    public List<Student> retrieveAllStudents() {
        return students;
    }

    private Student retrieveStudent(String studentId) {
        for (Student student: students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public List<Course> retrieveCourses(String studentId) {
        Student student = retrieveStudent(studentId);
        if (student == null) {
            return null;
        }
        return student.getCourses();
    }

    public Course retrieveCourse(String studentId, String courseId) {
        Student student = retrieveStudent(studentId);
        if (student == null) {
            return null;
        }
        for (Course course : student.getCourses()) {
            if (course.getCourse_Id().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    private SecureRandom random = new SecureRandom();

    public Course addCourse(String studentId, Course course) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        course.setCourse_Id(randomId);

        student.getCourses().add(course);

        return course;
    }







}
