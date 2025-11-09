package com.example.Webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.*;

import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Entity.TechnicianEntity;
public interface TechnicianRepository extends JpaRepository<TechnicianEntity,Long>{
		TechnicianEntity findByPhonenumberAndPassword(String phonenumber,String password);
		TechnicianEntity findByPhonenumber(String phonenumber);
		@Query("SELECT  DISTINCT  b FROM BookingEntity b WHERE b.id IN (" +
		           "SELECT t.booking.id FROM TechBookingEntity t " +
		           "WHERE t.date = :date AND t.technician.id = :id )")
		List<Object[]> findByTechId(long id,LocalDate date);
		
}
