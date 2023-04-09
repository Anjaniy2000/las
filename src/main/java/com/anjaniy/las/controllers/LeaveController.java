package com.anjaniy.las.controllers;

import com.anjaniy.las.models.dto.request.LeaveRequest;
import com.anjaniy.las.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/applyLeave")
    @ResponseStatus(HttpStatus.CREATED)
    public String leaveRequest(@RequestBody LeaveRequest leaveRequest) throws ParseException {
        leaveService.leaveRequest(leaveRequest);
        return "Leave Applied Successfully";
    }
}
