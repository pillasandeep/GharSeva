package com.example.Webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.*;
import com.example.Webapp.Entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
	List<BookingEntity> findByPhonenumber(String phonenumber);
	List<BookingEntity> findByDate(LocalDate date);
	List<BookingEntity> findByDateAndPhonenumber(LocalDate date,String phonenumber);
}
