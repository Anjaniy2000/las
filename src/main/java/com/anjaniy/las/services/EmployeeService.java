package com.anjaniy.las.services;

import com.anjaniy.las.exceptions.EmployeeNotFoundException;
import com.anjaniy.las.models.dto.EmployeeDto;
import com.anjaniy.las.models.entities.Employee;
import com.anjaniy.las.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    public EmployeeService(EmployeeRepository employeeRepository){
//        this.employeeRepository = employeeRepository;
//    }

    public void addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentName(employeeDto.getDepartmentName());

        employeeRepository.save(employee);
    }

    public EmployeeDto getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);

        if(employee == null){
            throw new EmployeeNotFoundException("Please enter a valid email");
        }
        else{
            //Mapping Entity to DTO:
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setDepartmentName(employee.getDepartmentName());

            return employeeDto;


//        SELECT * FROM EMPLOYEE WHERE email = "anjaniy01salekar@gmail.com";

        }
    }
}
