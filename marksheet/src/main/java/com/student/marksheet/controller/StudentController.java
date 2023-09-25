package com.student.marksheet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.student.marksheet.data.Student;
import com.student.marksheet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    public StudentService studentService;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/getOneStudent/{id}")
    public Student getOneStudent(@PathVariable("id") Integer id) {
        return studentService.getOneStudent(id);
    }

    @PostMapping("/sendEmailToParents")
    public ResponseEntity<String> sendMarksheetMailToOtherMicroservice() throws JsonProcessingException {
        studentService.getAStudent();
        return ResponseEntity.ok("Email sent to all parents");
    }
}
