package com.shan.forat.rest_project.rest;

import com.shan.forat.rest_project.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

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

        if(studentId>=theStudents.size()|| studentId<0){
            throw new StudentNotFoundException("Student id not found - "+studentId);
        }


        return  theStudents.get(studentId);
    }
}
