package com.oguzhan.springboot.thymeleafdemo.servicee;


import com.oguzhan.springboot.thymeleafdemo.DAO.EmployeeRepository;
import com.oguzhan.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    @Autowired
    public ServiceImpl( EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Optional<Employee> findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        }
        else{
            throw new RuntimeException("did not find employee ıd -"+theId);
        }
        return result;
    }

    @Override
    public void save(Employee theEmployee) {

        employeeRepository.save(theEmployee);

    }

    @Override
    public void update(Optional<Employee>  theEmployee) {
        employeeRepository.saveAndFlush(theEmployee.get());
    }

    @Override
    public void deleteById(int theId) {

        employeeRepository.deleteById(theId);
    }
}
