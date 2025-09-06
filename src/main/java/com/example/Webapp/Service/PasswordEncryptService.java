package com.example.Webapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Webapp.Entity.CustomerEntity;
import com.example.Webapp.Repository.CustomerRepository;

@Service
public class PasswordEncryptService {

	@Autowired
	private BCryptPasswordEncoder passwordencoder;
	@Autowired
	private CustomerRepository custrepo;
	public String rawPasswordToEncryptPassword(String password)
	{
		String temp=passwordencoder.encode(password);
		//System.out.println("Encryption password"+temp);
		return  temp;
	}
	public boolean login(String phone,String rawpassword)
	{
		
		CustomerEntity customer=custrepo.findByPhonenumber(phone);
		if(customer == null || customer.getPhonenumber()==(null))
			return false;
	//	System.out.println("Customer "+ customer.getPhonenumber());//
		String hashpassword=customer.getPassword();
		boolean status=passwordencoder.matches(rawpassword, hashpassword);
		if(status)
			return true;
		return false;
	}
}
