package com.example.Webapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import com.example.Webapp.Entity.AdminEntity;
import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Repository.AdminRepository;
import com.example.Webapp.Repository.BookingRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository repo;
	@Autowired
	BookingRepository bookrepo;
	public boolean ValidateAdmin(String phonenumber,String password)
	{
		AdminEntity x=repo.findByPhonenumberAndPassword(phonenumber, password);
		//System.out.println("this is AdminService class:"+x);
		return x!=null;
	}
	
	public List<BookingEntity> fetchRecords(LocalDate date, String phone) {
	    if (date != null && (phone == null || phone.isEmpty())) {
	        return bookrepo.findByDate(date);
	    } else if ((date == null) && (phone != null && !phone.isEmpty())) {
	        return bookrepo.findByPhonenumber(phone);
	    } else if (date != null && phone != null && !phone.isEmpty()) {
	        // Optional: if both are provided, you can choose what to do
	        // You can either prioritize one or create a custom query
	        return bookrepo.findByDateAndPhonenumber(date,phone); // or combine both if needed
	    } else {
	        return new ArrayList<>(); // Nothing provided
	    }
	}
	public List<BookingEntity> todayBookings(LocalDate date)
	{
		return bookrepo.findByDate(date);
	}

}
