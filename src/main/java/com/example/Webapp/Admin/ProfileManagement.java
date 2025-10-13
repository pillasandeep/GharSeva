package com.example.Webapp.Admin;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Entity.CustomerEntity;
import com.example.Webapp.Repository.BookingRepository;
import com.example.Webapp.Repository.CustomerRepository;
import com.example.Webapp.Service.AdminService;

@Controller
public class ProfileManagement {

	@Autowired
	AdminService adminservice;
	@Autowired 
	BookingRepository custrepo;
	@GetMapping("/customerprofile")
	public String  getCustomerProfile(@RequestParam(defaultValue = "0") int page,
										@RequestParam(defaultValue ="10") int size,
										Pageable pageable,
										Model model)
	{
	Page<BookingEntity> list= custrepo.findAll(PageRequest.of(page,size));	
	System.out.println(list);
	model.addAttribute("totalcustomers",list.getTotalElements());
	model.addAttribute("customer", list.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", list.getTotalPages());
    model.addAttribute("pageSize", size);
		 return "customerprofileadmin";
	}

	
}
