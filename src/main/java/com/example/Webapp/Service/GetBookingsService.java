package com.example.Webapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Repository.BookingRepository;
import java.util.*;
@Service
public class GetBookingsService {

	@Autowired
	BookingRepository bookrepo;
	public List<BookingEntity> getHistory(String phonenumber)
	{
		return bookrepo.findByPhonenumber(phonenumber);
	}
}
