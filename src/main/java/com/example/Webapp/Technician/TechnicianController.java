package com.example.Webapp.Technician;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Webapp.Entity.TechnicianEntity;
import com.example.Webapp.Repository.TechnicianRepository;

import java.util.*;
@Controller
public class TechnicianController {

	TechnicianRepository techrepo;
	TechnicianController(TechnicianRepository tech)
	{
		this.techrepo=tech;
	}
	
	@GetMapping("/techprofile")
	String getAllDetails(Model model)
	{
		List<TechnicianEntity> x=techrepo.findAll();
		model.addAttribute("profile",x);
		return "techprofile";
	}
}
