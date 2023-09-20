package com.student.marksheet.service;

import com.student.marksheet.data.Parent;
import com.student.marksheet.data.Student;
import com.student.marksheet.data.SubjectMark;
import com.student.marksheet.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Student addStudent(Student student){

        Parent parent = student.getParent();
        parent.setStudent(student);
        parentService.addParent(parent);

         studentRepo.save(student);

        List<SubjectMark> subjectMarks = student.getSubjectMarks();
        subjectMarks.forEach(subjectMark -> {
            subjectMark.setStudent(student);
            subjectMarksService.addSubjectMark(subjectMark);
        });
        return student;
    }
}
