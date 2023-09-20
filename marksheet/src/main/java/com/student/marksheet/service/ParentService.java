package com.student.marksheet.service;

import com.student.marksheet.data.Parent;
import com.student.marksheet.data.Student;
import com.student.marksheet.repository.ParentRepo;
import com.student.marksheet.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentService {

    @Autowired
    public ParentRepo parentRepo;

    @Autowired
    public StudentRepo studentRepo;
    public void addParent(Parent parent){
        Student student = parent.getStudent();
        studentRepo.save(student);
        parent.setStudent(student);
        parentRepo.save(parent);
    }
}
