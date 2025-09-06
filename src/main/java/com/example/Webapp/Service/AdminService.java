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
	        return bookrepo.findByPhonenumberOrderByDateDesc(phone);
	    } else if (date != null && phone != null && !phone.isEmpty()) {
	        // Optional: if both are provided, you can choose what to do
	        // You can either prioritize one or create a custom query
	        return bookrepo.findByDateAndPhonenumber(date,phone); // or combine both if needed
	    } else {
	        return new ArrayList<>(); // Nothing provided
	    }
	}
	public List<BookingEntity> todayBookings(LocalDate date,String state)
	{
		return bookrepo.findByDateAndState(date,state);
	}
	public List<Object[]> getMonthlyCatReport(int month,int year)
	{
		return bookrepo.getMonthlyReport(month,year);
	}
	public List<Object[]> getYearlyCatReport(int year)

	{
		return bookrepo.getYearlyCatReport(year);
	}
	public void moveToNextState(int id)
	{
		BookingEntity booking = bookrepo.findById(id);
		switch (booking.getState()) {
        case "Pending": booking.setState("Assigned"); break;
        case "Assigned": booking.setState("Completed"); break;
        case "Completed": break; // already done
			}

    bookrepo.save(booking);
    //assigned records are fetch here
    
    
    
    
	}
	public List<BookingEntity> todayBookingsOrderByStatus(LocalDate date)
	{
		return bookrepo.findByDateOrderByState(date);
	}

}
