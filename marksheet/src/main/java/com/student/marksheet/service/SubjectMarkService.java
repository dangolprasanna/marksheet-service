package com.student.marksheet.service;

import com.student.marksheet.data.Student;
import com.student.marksheet.data.SubjectMark;
import com.student.marksheet.repository.StudentRepo;
import com.student.marksheet.repository.SubjectMarkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectMarkService {

    @Autowired
    public SubjectMarkRepo subjectMarkRepo;

    @Autowired
    public StudentRepo studentRepo;

    public void addSubjectMark(SubjectMark subjectMark){
        Student student = subjectMark.getStudent();
        studentRepo.save(student);
        subjectMark.setStudent(student);
        subjectMarkRepo.save(subjectMark);
    }
}
