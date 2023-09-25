package com.student.marksheet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.student.marksheet.data.Parent;
import com.student.marksheet.data.Student;
import com.student.marksheet.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    public StudentRepo studentRepo;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ParentService parentService;
    @Autowired
    private SubjectMarkService subjectMarksService;

    @Transactional
    public Student addStudent(Student student) {

        Parent parent = student.getParent();
        parent.setStudent(student);
        parentService.addParent(parent);

        studentRepo.save(student);

//        List<SubjectMark> subjectMarks = student.getSubjectMarks();
//        subjectMarks.forEach(subjectMark -> {
//            subjectMark.setStudent(student);
//            subjectMarksService.addSubjectMark(subjectMark);
//        });
        return student;
    }

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public Student getOneStudent(Integer id) {
        return studentRepo.findById(id);
    }

    public void getAStudent() throws JsonProcessingException {
        List<Student> students = studentRepo.findAll();
        for (Student s1 : students) {
            sendEmailToParents(s1);
        }
    }

    public void sendEmailToParents(Student student) {
        try {
            String ROOT_URI = "http://localhost:8443/sendBulkEmail";
            ResponseEntity<Student> responseEntity = restTemplate.postForEntity(ROOT_URI, student, Student.class);

        } catch (RestClientException e) {
            // Handle exceptions (e.g., ResourceAccessException) here.
            e.printStackTrace(); // Consider logging the exception.
        }
    }

}
