package com.student.marksheet.repository;

import com.student.marksheet.data.SubjectMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectMarkRepo extends JpaRepository<SubjectMark,Long> {
}
