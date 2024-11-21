package com.form.employeepayroll.service;

import com.form.employeepayroll.exception.EmployeeNotFoundException;
import com.form.employeepayroll.model.Employee;
import com.form.employeepayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public List<Employee> getALlEmployees()
    {
        return employeeRepository.findAll();
    }

    public Employee findByName(String name)
    {
        return employeeRepository.findByName(name).orElseThrow(()->new EmployeeNotFoundException("Employee not found 404"));
    }

    public Employee updateEmployee(String name,Employee employee)
    {
        Employee existingEmployee= employeeRepository.findByName(name).orElseThrow(() -> new EmployeeNotFoundException("Employee not found 404"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setProfile(employee.getProfile());
        existingEmployee.setNotes(employee.getNotes());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setStartDate(employee.getStartDate());
        return employeeRepository.save(existingEmployee);
    }

    public String deleteEmployee(String name)
    {
        Employee employee=employeeRepository.findByName(name).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found 404"));
        employeeRepository.delete(employee);
        return "Employee with name "+name+" has been deleted!!";
    }
}
