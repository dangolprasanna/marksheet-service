package com.student.marksheet.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Table(name = "parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String emailAddress;
    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;
}
