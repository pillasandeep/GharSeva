package com.example.Webapp.CheckOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Entity.CartDetailsEntity;
import com.example.Webapp.Repository.BookingRepository;
import com.example.Webapp.Repository.CartRepository;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
@Controller
public class CheckOut {

	@Autowired
	CartRepository cartrepo;
	@Autowired
	BookingRepository bookrepo;
	@PostMapping("checkout")
	public String checkOut(@ModelAttribute BookingEntity bookingForm,Model model,HttpSession session)
	{
		String phone=(String)session.getAttribute("phonenumber");
		if(phone==null || phone.isEmpty())
		{
			model.addAttribute("error", "User not logged in.");
			return "redirect:/login";
		}
		
		List<CartDetailsEntity>list=cartrepo.findByPhonenumber(phone);
		if(list.isEmpty())
		{
			 model.addAttribute("error", "Cart is empty. Please add a service before checkout.");
			 return "redirect:/home";
		}
		if (bookingForm.getName() == null || bookingForm.getName().isBlank()
	            || bookingForm.getAddress() == null || bookingForm.getAddress().isBlank()) {
	        model.addAttribute("error", "Name and Address are required.");
	        model.addAttribute("booking", bookingForm); // preserve form data
	        return "checkout";
	    }

			
		for(CartDetailsEntity cart:list)
		{
			BookingEntity book=new BookingEntity();
			book.setServicename(cart.getServicename());
			book.setName(bookingForm.getName());
			book.setAddress(bookingForm.getAddress());
			book.setLandmark(bookingForm.getLandmark());
			book.setPhonenumber(phone);
			book.setSphonenumber(bookingForm.getSphonenumber());
			book.setDate(LocalDate.now());
			book.setTime(LocalTime.now());
			book.setPrice(cart.getPrice());
			bookrepo.save(book);
			
		}
		cartrepo.deleteAll(list);
		return "successpage";
	}
	
}
