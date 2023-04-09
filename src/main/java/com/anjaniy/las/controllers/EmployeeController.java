package com.anjaniy.las.controllers;

import com.anjaniy.las.models.dto.common.EmployeeDto;
import com.anjaniy.las.services.CsvExportService;
import com.anjaniy.las.services.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CsvExportService csvExportService;

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

    @DeleteMapping("/deleteEmployee/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployee(@PathVariable("employeeId") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return "Employee Deleted Successfully";
    }

    @GetMapping("/downloadCSV")
    public void getAllEmployeesInCSV(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvExportService.writeEmployeesToCsv(servletResponse.getWriter());
    }
}
