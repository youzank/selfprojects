package com.oguzhan.springboot.thymeleafdemo.controller;

import com.oguzhan.springboot.thymeleafdemo.entity.Employee;
import com.oguzhan.springboot.thymeleafdemo.servicee.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
    //load employee data

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model theModel){
        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees", theEmployees);
        return "/employees/employees-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee",theEmployee);
        return"employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel){
        //get the employee from the service
       Optional<Employee> theEmployee = employeeService.findById(theId);
        //set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee",theEmployee);
        System.out.println(theEmployee);
        employeeService.update(theEmployee);
        // send over to our form
        return "employees/employee-form";
    }



    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the employee
        employeeService.save(theEmployee);
        //use a redirect to prevent duplicate submissions
        return"redirect:/employees/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId")int theId){
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }
}
