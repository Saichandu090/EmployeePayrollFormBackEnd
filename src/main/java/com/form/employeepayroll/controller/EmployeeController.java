package com.form.employeepayroll.controller;

import com.form.employeepayroll.model.Employee;
import com.form.employeepayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee)
    {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.ACCEPTED);
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<?> getAllEmployees()
    {
        return new ResponseEntity<>(employeeService.getALlEmployees(),HttpStatus.FOUND);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name)
    {
        return new ResponseEntity<>(employeeService.findByName(name),HttpStatus.FOUND);
    }

    @PutMapping("/updateEmployee/{name}")
    public ResponseEntity<?> updateEmployee(@PathVariable String name,@RequestBody Employee employee)
    {
        return new ResponseEntity<>(employeeService.updateEmployee(name,employee),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteEmployee/{name}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String name)
    {
        return new ResponseEntity<>(employeeService.deleteEmployee(name),HttpStatus.ACCEPTED);
    }
}
