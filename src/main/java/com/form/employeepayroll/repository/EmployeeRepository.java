package com.form.employeepayroll.repository;

import com.form.employeepayroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{
    Optional<Employee> findByName(String name);
}
