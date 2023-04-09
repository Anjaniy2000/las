package com.anjaniy.las.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    private String leaveDescription;
    private String startDate;
    private String endDate;
    private Long employeeId;
}

