package com.epidata.walkAndBuy.web;

import java.io.Serializable;
import java.util.List;

import com.epidata.walkAndBuy.model.Customer;
import com.epidata.walkAndBuy.service.CustomerService;

@SuppressWarnings("serial")
public class CustomerManagedBean implements Serializable {
	// DI via Spring
	private CustomerService customerService;
	public String name;
	public String address;

	// get all customer data from database
	public List<Customer> getCustomerList() {
		return customerService.findAllCustomer();
	}
	
	// clear form values
		private void clearForm() {
			setName("");
			setAddress("");
		}

	// add a new customer data into database
	public String addCustomer() {
		Customer cust = new Customer();
		cust.setName(getName());
		cust.setAddress(getAddress());
		customerService.addCustomer(cust);
		clearForm();
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
