package com.anjaniy.las.repositories;

import com.anjaniy.las.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
//    Employee findByFirstName(String firstName);
//
//    Employee findByFirstNameAndLastName(String firstName, String lastName);
//
//    Employee findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
