/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduit.spring;

import java.util.List;

/**
 *
 * @author rafaeli
 */
public interface EmployeeDAO {
    
    public void saveEmployee(Employee employee);
    public List<Employee> getAllEmployees();
    public Employee getEmployee(int id);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(int id);
    
}
