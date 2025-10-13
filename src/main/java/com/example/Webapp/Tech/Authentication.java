package com.example.Webapp.Tech;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Webapp.Entity.TechnicianEntity;
import com.example.Webapp.Service.TechnicianService;

import jakarta.servlet.http.HttpSession;
@Controller
public class Authentication {
	@Autowired
	TechnicianService techserv;
	@GetMapping("/tech_login")
	String loadLoginPage(Model model)
	{
		
		model.addAttribute("tech",new TechnicianEntity());
		model.addAttribute("error","");

		return "tech_login";
	}
	@PostMapping("/techlogin")
	@ResponseBody
	String loginPage(Model model,@ModelAttribute TechnicianEntity tech,HttpSession session)
	
	{
		String phone=tech.getPhonenumber();
		String password=tech.getPassword();
		
		
		TechnicianEntity list=techserv.isValidUser(phone, password);
		
		if(list==null)
		return "invalid";
		else
		{
			session.setAttribute("phonenumber",phone);
			System.out.println(list.getName()+" "+list.getType());
			return "login";
		}
		
	}
}
