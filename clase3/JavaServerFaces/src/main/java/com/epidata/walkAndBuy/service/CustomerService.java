package com.epidata.walkAndBuy.service;
 
import java.util.List;

import com.epidata.walkAndBuy.model.Customer;
 
public interface CustomerService{
 
	public void addCustomer(Customer customer);
 
	public List<Customer> findAllCustomer();
 
}