package com.example.Webapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Webapp.Entity.CustomerEntity;
import com.example.Webapp.Repository.CustomerRepository;
@Service
public class CustomerService {
	@Autowired
	CustomerRepository repo;
	public boolean validateLogin(String phonenumber,String password)
	{
		CustomerEntity customer=repo.findByPhonenumberAndPassword(phonenumber,password);
		return customer!=null;
	}
	
	public CustomerEntity ProfileDetails(String phonenumber)
	{
		CustomerEntity customer=repo.findByPhonenumber(phonenumber);
		return customer;
	}
}
