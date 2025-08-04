package com.example.Webapp.Profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Webapp.Authentication.AuthenticationController;
import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Entity.CustomerEntity;
import com.example.Webapp.Repository.BookingRepository;
import com.example.Webapp.Repository.CustomerRepository;
import com.example.Webapp.Service.CustomerService;
import com.example.Webapp.Service.GetBookingsService;
import java.util.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerProfile {
	
	@Autowired
	AuthenticationController autho;
	@Autowired
	CustomerRepository repo;
	@Autowired
	CustomerService service;
	@Autowired
	GetBookingsService getrepo;
	@Autowired BookingRepository bookrepo;
	@GetMapping("profile")
	public String ProfileDetails(Model model,HttpSession session)
	{
	String phonenumber=(String)session.getAttribute("phonenumber");	
	if(phonenumber!=null)
	{
		CustomerEntity customer=service.ProfileDetails(phonenumber);
		model.addAttribute("customer",customer);
		{
			String phone=(String)session.getAttribute("phonenumber");
			List <BookingEntity>list=getrepo.getHistory(phone);
			model.addAttribute("historyList", list);
		}
		return "profile";
	}
	else
	{
		return "redirect:/login1";
	}
	
	}
	
	
	
}
