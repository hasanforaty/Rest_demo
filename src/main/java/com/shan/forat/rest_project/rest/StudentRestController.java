package com.shan.forat.rest_project.rest;

import com.shan.forat.rest_project.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
    @PostConstruct
    void init(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student(
                "sahan","foraty"
        ));
        theStudents.add(new Student(
                "hasan","hotmail"
        ));
        theStudents.add(new Student(
                "Zahra","Yahoo"
        ));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        return  theStudents.get(studentId);
    }
}
