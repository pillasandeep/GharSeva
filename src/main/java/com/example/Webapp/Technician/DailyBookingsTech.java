package com.example.Webapp.Technician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Entity.TechnicianEntity;
import com.example.Webapp.Repository.TechnicianRepository;
import com.example.Webapp.Service.TechnicianService;

import java.time.LocalDate;
import java.util.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class DailyBookingsTech {

	@Autowired
	TechnicianService techservice;

	@Autowired
	TechnicianRepository techrepo;
	@GetMapping("/DailyTechnician")
	//@ResponseBody
	String  getRecords(HttpSession session,Model model)
	{
		String phone=(String)session.getAttribute("phonenumber");
		TechnicianEntity techent = techrepo.findByPhonenumber(phone);
		long id=techent.getId();
		
		List<Object[]> te= techservice.getTodayDate(id,LocalDate.now());
		model.addAttribute("bookings", te);
		//return te;
		return "DailyTechnician";
	}
	
	
}
