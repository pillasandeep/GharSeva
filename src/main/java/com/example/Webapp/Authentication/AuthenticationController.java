package com.example.Webapp.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.Webapp.Entity.CustomerEntity;
import com.example.Webapp.Repository.CustomerRepository;
import com.example.Webapp.Service.AdminService;
import com.example.Webapp.Service.CustomerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
   
	@Autowired
	CustomerRepository repo;
	@Autowired
	CustomerService service;
	@Autowired
	AdminService adminservice;
	@GetMapping("/register")
	public String CustomerRegistration(Model model)
	{
		model.addAttribute("customer",new CustomerEntity());
		//model.addAttribute("admin",new AdminEntity());
		model.addAttribute("error","");
		//model.addAttribute("error1","");
		return "registration";
	}
	@PostMapping("/register")
	public String addCustomer(
	        @Valid @ModelAttribute("customer") CustomerEntity customer,
	        BindingResult result,
	        Model model) {

	    // Manual check for phone length (if not using @Pattern in entity)
	    if (customer.getPhonenumber() == null || customer.getPhonenumber().length() != 10) {
	        result.rejectValue("phonenumber", "error.customer", "Phone number must be exactly 10 digits");
	    }

	    // Manual check for password match
	    if (!customer.getPassword().equals(customer.getConfirmpassword())) {
	        result.rejectValue("confirmpassword", "error.customer", "Passwords do not match");
	    }

	    // If there are any errors, return the same page with them
	    if (result.hasErrors()) {
	        return "registration"; // stays on register.html with error messages
	    }

	    // Save the customer
	    repo.save(customer);

	    return "redirect:/login1";
	}

	@GetMapping("/login1")
	public String LoadLogin(Model model)
	{
		model.addAttribute("customer",new CustomerEntity());
		//model.addAttribute("admin",new AdminEntity());
		return "login1";
	}
	@PostMapping("/login1")
	public String LoginCustomer(@ModelAttribute CustomerEntity customer,Model model,HttpSession session)
	{
		boolean check=service.validateLogin(customer.getPhonenumber(),customer.getPassword());
		//session.removeAttribute("cartItems");
		session.setAttribute("phonenumber", customer.getPhonenumber());
		if(check)
		{
			session.setAttribute("phonenumber",customer.getPhonenumber());
			String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
	        if (redirectUrl != null) {
	            session.removeAttribute("redirectAfterLogin"); // Clean up
	            return "redirect:" + redirectUrl;
	        }
			return "redirect:/home";
		}
		else
		{
			model.addAttribute("error","Invalid Phonenumber or Password");
			model.addAttribute("customer",new CustomerEntity());
		//	model.addAttribute("admin",new AdminEntity());

			return "login1";
		}
	}
	
	@GetMapping("/home")
	public String LoadHomePage(Model model)
	{
		//model.addAttribute("home", model);
		return "home";
	}
	@GetMapping("/logout")
	public String logOut(HttpSession session)
	{
//		System.out.print("chcek");
//		System.out.println(session.getAttribute("phonenumber"));
		session.invalidate();
		
		return "redirect:/login1";
	}
	
}
