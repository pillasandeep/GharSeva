package com.example.Webapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Webapp.Entity.TechnicianEntity;
import com.example.Webapp.Repository.TechnicianRepository;
@Service
public class TechnicianService {
	@Autowired
	TechnicianRepository techrepo;
	
	public TechnicianEntity isValidUser(String phonenumber, String password)
	{
		return techrepo.findByPhonenumberAndPassword(phonenumber, password);
	}
	
	
}
