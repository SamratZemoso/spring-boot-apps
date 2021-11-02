package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// if we want to change the path name we can mention below annotation
// @RepositoryRestResource(path = "/members")

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {



}
