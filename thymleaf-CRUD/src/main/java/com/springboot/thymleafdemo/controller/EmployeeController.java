package com.springboot.thymleafdemo.controller;

import com.springboot.thymleafdemo.entity.Employee;
import com.springboot.thymleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // show the new employee add form
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        // create an employee
        Employee employee = new Employee();

        // assign an attribute
        model.addAttribute("employee", employee);

        // return the form
        return "employee/employee-form";

    }

    // save the details of employee after submitting
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "employee/employee-form";
        }

        // save the employee details
        employeeService.save(employee);

        // redirect to list page
        return "redirect:/employee/list";

    }

    // update the employee
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {

        Employee employee = employeeService.findById(id);

        model.addAttribute("employee", employee);

        return "employee/employee-form";

    }

    // delete the Employee
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {

        employeeService.deleteById(id);

        return "redirect:/employee/list";

    }

    // get all the employees
    @GetMapping("/list")
    public String listEmployees(Model model) {

        List<Employee> theEmployees = employeeService.findAll();

        model.addAttribute("employees", theEmployees);

        return "list-employee";
    }

}
