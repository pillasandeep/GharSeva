package com.example.Webapp.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Webapp.Entity.AdminEntity;
import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Service.AdminService;
import java.time.LocalDate;
import jakarta.servlet.http.HttpSession;
import java.util.*;
@Controller
public class AdminDashBoardController {
	@Autowired
	AdminService adminservice;
	@GetMapping("/admindashboard")
	public String  LoadDashBoard(Model model,HttpSession session)
	{
		if(session.getAttribute("admin")!=null)
		{
			List<BookingEntity>service=adminservice.todayBookings(LocalDate.now());
			model.addAttribute("todaysBookings", service);
			return "admindashboard";
		}
		else
			return "redirect:/adminlogin";
		
		//return "redirect:/admindashboard";
	}
	@GetMapping("/adminlogin")
	public String LoadAdmin(Model model)
	{
		model.addAttribute("admin",new AdminEntity());
		//model.addAttribute("customer",new CustomerEntity());
		//model.addAttribute("error","");
		model.addAttribute("error1","");

		return "adminlogin";
	}
	@PostMapping("/adminlogin")
	public String LoginAdmin(@ModelAttribute AdminEntity admin,Model model,HttpSession session)
	{
		boolean c=adminservice.ValidateAdmin(admin.getPhonenumber(),admin.getPassword());
		if(c)
		{
			session.setAttribute("admin",admin.getPhonenumber());
			model.addAttribute("message", "Login successful.");
			// model.addAttribute("admin", new AdminEntity());       // reset form if needed
		       // model.addAttribute("customer", new CustomerEntity());
		        return "redirect:/admindashboard";
		}
		else
		{
			model.addAttribute("error1","Invalid Password or Phonenumber");
			model.addAttribute("admin",new AdminEntity());
			//model.addAttribute("customer",new CustomerEntity());

			return "adminlogin";
		}
	}
	@GetMapping("/logout1")
	public String logOut(HttpSession session)
	{
		System.out.println(session.getAttribute("admin"));
		session.invalidate();
		return "redirect:/adminlogin";
	}
}
