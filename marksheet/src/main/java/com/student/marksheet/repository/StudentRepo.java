package com.student.marksheet.repository;

import com.student.marksheet.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
    public Student findById(Integer id);
}
