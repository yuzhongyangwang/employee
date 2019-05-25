package com.thoughtworks.employee.controller;

import com.thoughtworks.employee.entity.Employee;
import com.thoughtworks.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> employeeList() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findEmployee(@PathVariable Integer id) {
        return employeeRepository.findById(id).get();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee modifyEmployee(@RequestBody Employee employee,@PathVariable Integer id) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String,String> deleteEmployee(@PathVariable Integer id) {
        Map<String, String> map = new HashMap<>();
        try {
            employeeRepository.deleteById(id);
            map.put("status", "success");
        } catch (Exception e) {
            map.put("status", "fail");
        }
        return map;
    }
}
