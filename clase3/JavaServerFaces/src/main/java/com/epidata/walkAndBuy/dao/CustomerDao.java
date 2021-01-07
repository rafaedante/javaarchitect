package com.epidata.walkAndBuy.dao;
 
import java.util.List;

import com.epidata.walkAndBuy.model.Customer;
 
public interface CustomerDao{
 
	public void addCustomer(Customer customer);
 
	public List<Customer> findAllCustomer();
 
}