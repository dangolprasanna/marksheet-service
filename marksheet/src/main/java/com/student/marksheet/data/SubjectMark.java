package com.student.marksheet.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Table(name = "subjectmark")

public class SubjectMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;
    private double mark;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;
}
