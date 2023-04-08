package com.anjaniy.las.controllers;

import com.anjaniy.las.models.dto.EmployeeDto;
import com.anjaniy.las.models.entities.Employee;
import com.anjaniy.las.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Constructor Dependency Injection:
//    public EmployeeController(EmployeeService employeeService){
//        this.employeeService = employeeService;
//    }

    @GetMapping("/greet")
    public String greet(){
        return "Good morning";
    }

    @PostMapping("/addEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto);
        return "Employee Added Successfully";
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployeeByEmail(@PathVariable("email") String email){
        return employeeService.getEmployeeByEmail(email);
    }
}
