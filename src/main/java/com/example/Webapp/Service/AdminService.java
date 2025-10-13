package com.example.Webapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;

import com.example.Webapp.Entity.AdminEntity;
import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Entity.CustomerEntity;
import com.example.Webapp.Repository.AdminRepository;
import com.example.Webapp.Repository.BookingRepository;
import com.example.Webapp.Repository.CustomerRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository repo;
	@Autowired
	BookingRepository bookrepo;
	@Autowired
	BookingRepository customerRepo;
	public boolean ValidateAdmin(String phonenumber,String password)
	{
		AdminEntity x=repo.findByPhonenumberAndPassword(phonenumber, password);
		//System.out.println("this is AdminService class:"+x);
		return x!=null;
	}
	
	public List<Object[]> fetchRecordsWithTechnician(LocalDate date, String phone) {
	    if (date != null && (phone == null || phone.isEmpty())) {
	        return customerRepo.findBookingsWithTechnician(date);
	    }
	    else
	    	return customerRepo.findBookingsByDateAndPhone(date,phone);
	    // Add more conditions if needed
	    //return new ArrayList<>();
	}

	public List<Object[]> todayBookings(LocalDate date,String state)
	{
		
			return bookrepo.getAssignedBookings(state,date);
	
	}
	public List<BookingEntity> todayBookingsDuplicate(LocalDate date,String state)
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
	public void moveToNextState(long id)
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
	//Csutomer profile management in admin protel to show 
//	public List<BookingEntity> getCustomerProfile(Pageable pageable)
//	{
//		System.out.println("I'm Called by contrleer");
//		return customerRepo.findAll(pageable).getContent();
//	}

}
