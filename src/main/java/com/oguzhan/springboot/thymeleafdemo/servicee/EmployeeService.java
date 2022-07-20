package com.oguzhan.springboot.thymeleafdemo.servicee;

import com.oguzhan.springboot.thymeleafdemo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> findAll();
    public Optional<Employee> findById(int theId);
    public void save (Employee theEmployee);
    public void update (Optional<Employee>  theEmployee);
    public void deleteById(int theId);



}
