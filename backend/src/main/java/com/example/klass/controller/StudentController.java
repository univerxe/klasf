package com.example.klass.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.klass.model.Student;

import jakarta.servlet.http.HttpServletRequest;


@RestController 
@CrossOrigin(origins = "*")
public class StudentController {

    private final List<Student> students = new ArrayList<>(Arrays.asList(
        new Student(1, "uni3", "1", "email", "04504", "dd"), 
        new Student(2, "Jane Doe", "2", "email2", "04505", "dd")
    ));

    @GetMapping("/students")
    public List<Student> getStudents() {
       return students;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);  
        return student;
    }

    @GetMapping("/csrf_token") 
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");    
    }
    
}
