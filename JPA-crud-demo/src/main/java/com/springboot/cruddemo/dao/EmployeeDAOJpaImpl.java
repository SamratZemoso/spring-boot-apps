package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {

        // create query
        Query query = entityManager.createQuery("from Employee");

        // execute query
        List<Employee> employees = query.getResultList();

        // return results
        return employees;

    }

    @Override
    public Employee findById(int id) {

        // get the employee by ID
        Employee employee = entityManager.find(Employee.class, id);

        // return the employee
        return employee;
    }

    @Override
    public void save(Employee theEmployee) {

        // save or update
        Employee dbEmployee = entityManager.merge(theEmployee);

        // update id from db ... we get generated ID
        theEmployee.setId(dbEmployee.getId());

    }

    @Override
    public void deleteById(int id) {

        // delete employee with primary Key
        Query query = entityManager.createQuery("delete from Employee where id=:employeeId");

        query.setParameter("employeeId", id);

        query.executeUpdate();

    }
}
