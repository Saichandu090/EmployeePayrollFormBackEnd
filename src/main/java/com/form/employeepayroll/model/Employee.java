package com.form.employeepayroll.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Blob profile;
    private String gender;
    private double salary;
    private LocalDate startDate;
    private String notes;

    @ElementCollection
    @CollectionTable(name = "dept_table", joinColumns = @JoinColumn(name = "name"))
    private List<String> department;
}
