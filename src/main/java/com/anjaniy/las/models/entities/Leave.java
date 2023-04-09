package com.anjaniy.las.models.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "leave_table")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    private String leaveDescription;
    private Date startDate;
    private Date endDate;
    private Long duration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employee;
}

//One employee can have multiple leaves, Multiple leaves can be associated to a single employee
