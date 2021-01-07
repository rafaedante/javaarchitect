package com.epidata.walkAndBuy.service.impl;
 
import java.util.List;

import com.epidata.walkAndBuy.dao.CustomerDao;
import com.epidata.walkAndBuy.model.Customer;
import com.epidata.walkAndBuy.service.CustomerService;
 
public class CustomerServiceImpl implements CustomerService{
 
	private CustomerDao customerDao;
 
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
 
	public void addCustomer(Customer customer){
 
		customerDao.addCustomer(customer);
 
	}
 
	public List<Customer> findAllCustomer(){
 
		return customerDao.findAllCustomer();
	}
}