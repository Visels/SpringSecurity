package com.sec.demo.Student;


import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Dylan Koech"),
            new Student(2, "Valarie Jerop"),
            new Student( 3, "Cynthia Jeruto")
    );

    @GetMapping
    public List<Student> getAllStudents(){
        System.out.println("Get all students was called");
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("New student was registered:");
        System.out.println(student);

    }

    @DeleteMapping(path = {"studentId"})
    public void deleteStudent(@PathVariable Integer studentId){
        System.out.println("A student was deleted:");
        System.out.println(studentId);
    }

    @PutMapping(path= {"studentId"})
    public void updateStudent(@PathVariable Integer studentId,@RequestBody Student student){
        System.out.println("A student was updated:");
        System.out.println(String.format("%s %s", studentId, student));
    }
}