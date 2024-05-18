package com.example.ris_lab3_1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

 /*   @OneToMany(mappedBy = "employee")
    private List<Task> tasks;*/
}