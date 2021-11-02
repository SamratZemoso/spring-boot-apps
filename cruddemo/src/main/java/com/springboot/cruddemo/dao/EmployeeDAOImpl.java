package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    // field for entity Manager
    @Autowired
    private EntityManager entityManager;

    @Override
    public List findAll() {

        // get the current session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);

        List<Employee> employees = query.getResultList();

        return employees;

    }

    @Override
    public Employee findById(int id) {

        // get the current session
        Session session = entityManager.unwrap(Session.class);

        // query for getting by id
        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void save(Employee theEmployee) {

        // get the current session
        Session session = entityManager.unwrap(Session.class);

        // save the employee
        session.saveOrUpdate(theEmployee);

    }

    @Override
    public void deleteById(int id) {

        // get the current session
        Session session = entityManager.unwrap(Session.class);

        // delete employee by Id
        Query query = session.createQuery("delete from Employee where id=:employeeId");

        query.setParameter("employeeId", id);

        query.executeUpdate();

    }
}
