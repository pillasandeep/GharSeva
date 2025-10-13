//dailywisereport fetch with date or phone number it's use for search particular user with date
package com.example.Webapp.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Service.AdminService;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.*;
@Controller
public class DailyBookings {
	@Autowired
	AdminService adminservice;
	@GetMapping("/dailybookings")
	public String LoadDailyBookings(Model model,HttpSession session) {
		System.out.println("sabdeesandeep"+session.getAttribute("admin"));
		if(session.getAttribute("admin")!=null)
		{
		    model.addAttribute("getbooking", new BookingEntity()); // form object
		    model.addAttribute("bookings", new ArrayList<>()); // empty booking list initially
		    return "dailybookings";
		}
		else
		{
			System.out.println("sandeep"+session.getAttribute("admin"));
			return "redirect:/adminlogin";
		}
	}

	@GetMapping("/getRecords")
	public String getBookingRecords(@ModelAttribute BookingEntity getbooking, Model model)
	{
	    LocalDate date = getbooking.getDate();
	    String phone = getbooking.getPhonenumber();

	    List<Object[]> dailyservices = adminservice.fetchRecordsWithTechnician(date, phone);
	   // List<Object[]> results = bookingRepository.findBookingsWithTechnician(date);

	    for (Object[] row : dailyservices) {
	        BookingEntity bookings = (BookingEntity) row[0];  // cast the first element
	        String technicianName = (String) row[1];         // cast the second element

	        System.out.println("Booking ID: " + bookings.getId());
	        System.out.println("Service Name: " + bookings.getServicename());
	        System.out.println("Technician Name: " + technicianName);
	    }


	    model.addAttribute("bookings", dailyservices);
	   // model.addAttribute("technicain, dailyservices)
	    model.addAttribute("getbooking", getbooking); // re-bind the form input

	    return "dailybookings"; // render this view
	}

	
	
	
	
	
	
	
	
	
}
