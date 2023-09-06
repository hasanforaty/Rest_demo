package com.shan.forat.rest_project.rest;

import com.shan.forat.rest_project.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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







    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create a studentErrorResponse
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        //Return Response Entity
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception){
        //create a studentErrorResponse
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        //Return Response Entity
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }



}
