package com.springboot.cruddemo.rest;

import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // getting all the employee's
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }


    // getting employee by ID
    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") int empId) {
        Employee theEmployee = employeeService.findById(empId);

        if(theEmployee == null) {
            throw new RuntimeException("Employee Id not found");
        }

        return theEmployee;
    }


    // adding a new employee
    @PostMapping("/addEmployee")
    // add @ResponseBody before return type to avoid Error - POST is not supported
    public @ResponseBody void addEmployee(@RequestBody Employee employee) {

        // save the employee object
        employeeService.save(employee);
    }


    // delete employee by ID
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") int id) {

        Employee employee = employeeService.findById(id);

        if(employee == null) {
            throw new RuntimeException("Employee ID not found: " + id);
        }

        // delete by ID
        employeeService.deleteById(id);

        return "Deleted Employee ID - " + id;
    }


    // update an Employee by ID
    @PutMapping("/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee) {

        employeeService.save(employee);

        return employee;

    }


}
