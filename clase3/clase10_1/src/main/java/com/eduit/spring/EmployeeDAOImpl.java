/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduit.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafaeli
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }     

    @Override
    public void saveEmployee(Employee employee) {
        String sql = "insert into Employee values (?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]
        {employee.getId(), employee.getName(), employee.getDept(), employee.getAge()});
    }

    @Override
    public List<Employee> getAllEmployees() {
        
        String sql = "select * from Employee";
        
        List<Employee> employeeList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Employee>>()
        {
            public List<Employee> extractData(ResultSet rs) throws SQLException {
                List<Employee> list = new ArrayList<Employee>();
                while(rs.next()){
                    Employee employee = new Employee();
                    employee.setId(rs.getInt(1));
                    employee.setName(rs.getString(2));
                    employee.setDept(rs.getString(3));
                    employee.setAge(rs.getInt(4));
                    list.add(employee);
                }
                return list;
            }        
        });
        
        return employeeList;        
    }

    @Override
    public Employee getEmployee(int id) {
        String sql = "select * from Employee where id = ?";
        Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[]
         {id}, new RowMapper<Employee>()
         { 
             public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
                 Employee employee = new Employee();
                 employee.setId(rs.getInt(1));
                 employee.setName(rs.getString(2));
                 employee.setDept(rs.getString(3));
                 employee.setAge(rs.getInt(4));
                 return employee;
             }         
         });
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "update Employee set name=?, dept=?, age=? where id =?";
        jdbcTemplate.update(sql, new Object[]
        {employee.getName(), employee.getDept(), employee.getAge(), employee.getId()});
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "delete from Employee where id = ?";
        jdbcTemplate.update(sql, new Object[]
        {id});
    }
    
}
