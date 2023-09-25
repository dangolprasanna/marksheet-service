package com.student.marksheet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.student.marksheet.data.Parent;
import com.student.marksheet.data.Student;
import com.student.marksheet.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    public StudentRepo studentRepo;

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
        for (int i = 0; i < students.size(); i++) {
            Student s1 = students.get(i);
            String emailJson = s1.toString();
            sendEmailToParents(emailJson);
        }
    }

    public void sendEmailToParents(String emailJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //HttpEntity<String> requestEntity = new HttpEntity<>(emailJson, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/sendMarksheetMail", emailJson, String.class);

        } catch (HttpClientErrorException e) {
            // Handle the exception
            e.printStackTrace();
        }
        ResponseEntity.ok("Sent");
    }
}
