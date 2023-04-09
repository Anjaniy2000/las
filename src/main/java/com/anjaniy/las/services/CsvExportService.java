package com.anjaniy.las.services;

import com.anjaniy.las.models.entities.Employee;
import com.anjaniy.las.repositories.EmployeeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CsvExportService {
    private static final Logger log = getLogger(CsvExportService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public void writeEmployeesToCsv(PrintWriter writer) {

        List<Employee> employees = employeeRepository.findAll();
        //SELECT * FROM EMPLOYEE;

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("EmployeeID", "First_Name", "Last_Name","Email","Department_Name", "Remaining_Leaves");
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getDepartmentName(), employee.getRemainingLeaves());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}
