package com.anjaniy.las.services;

import com.anjaniy.las.exceptions.EmployeeNotFoundException;
import com.anjaniy.las.exceptions.LeaveException;
import com.anjaniy.las.models.dto.request.LeaveRequest;
import com.anjaniy.las.models.entities.Employee;
import com.anjaniy.las.models.entities.Leave;
import com.anjaniy.las.repositories.EmployeeRepository;
import com.anjaniy.las.repositories.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LeaveService {
    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public void leaveRequest(LeaveRequest leaveRequest) throws ParseException {
        Employee employee = employeeRepository
                .findById(leaveRequest.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id" + leaveRequest.getEmployeeId()));

        Date startDate = format.parse(leaveRequest.getStartDate());
        Date endDate = format.parse(leaveRequest.getEndDate());

        long duration = endDate.getTime() - startDate.getTime();
        long days_difference = (duration / (1000*60*60*24)) % 365;

        if(employee.getRemainingLeaves() > 0 && employee.getRemainingLeaves() >= days_difference){
            Leave leave = new Leave();
            leave.setLeaveDescription(leaveRequest.getLeaveDescription());
            leave.setStartDate(startDate);
            leave.setEndDate(endDate);
            leave.setDuration(days_difference);
            leave.setEmployee(employee);
            employee.setRemainingLeaves(employee.getRemainingLeaves() - days_difference);
            leaveRepository.save(leave);
            employeeRepository.save(employee);
        } else{
            throw new LeaveException("You have insufficient leaves");
        }

    }
}

