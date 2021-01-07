/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduit.spring;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rafaeli
 */
@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeeDAOImpl employeeDAO;
    
    @RequestMapping(value="/employee", method=RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee){
        try {
            if (employeeDAO.getEmployee(employee.getId()) != null)
                employeeDAO.updateEmployee(employee);
        }catch(EmptyResultDataAccessException e){
            employeeDAO.saveEmployee(employee);
        }
        return new ModelAndView("redirect:/employees");
    }
    
    @RequestMapping(value="/edit/{id}")
    public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee,@PathVariable("id") int id){
        
        ModelAndView model = new ModelAndView("employees");
        employee = employeeDAO.getEmployee(id);
        List<Employee> employeeList =employeeDAO.getAllEmployees();
        model.addObject("employee", employee );
        model.addObject("employeeList", employeeList );
        return model;
    }
    
    
    
}
