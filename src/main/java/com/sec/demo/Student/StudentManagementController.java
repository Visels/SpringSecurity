package com.sec.demo.Student;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")

    public ResponseEntity getAllStudents(){
        System.out.println("Get all students was called");
        return new ResponseEntity(STUDENTS, HttpStatus.OK);
    }

   @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("New student was registered:");
        System.out.println(student);

    }

    @DeleteMapping(path = {"studentId"})
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity deleteStudent(@PathVariable Integer studentId){
        System.out.println("A student was deleted:");
        System.out.println(studentId);

        String result = "The student was deleted";

        return new ResponseEntity(result, HttpStatus.OK);
    }



    @PutMapping(path= {"studentId"})
    @PreAuthorize("hasAuthority('student:write')")

    public String updateStudent(@PathVariable Integer studentId,@RequestBody Student student){
        System.out.println("A student was updated:");
        System.out.println(String.format("%s %s", studentId, student));

        String result = "The student was updated successfully";

        return result;
    }

}


