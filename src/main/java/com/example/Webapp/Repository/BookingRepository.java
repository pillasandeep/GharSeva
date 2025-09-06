package com.example.Webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Webapp.Entity.BookingEntity;

import java.time.LocalDate;
import java.util.*;
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
	List<BookingEntity> findByPhonenumberOrderByDateDesc(String phonenumber);
	List<BookingEntity> findByDateAndState(LocalDate date,String state);
	List<BookingEntity> findByDate(LocalDate date);
	BookingEntity findById(int id);
	List<BookingEntity> findByDateAndPhonenumber(LocalDate date,String phonenumber);
	@Query("SELECT b.servicename,COUNT(b),SUM(b.price) " +
		       "FROM BookingEntity b " +
		       "WHERE FUNCTION('MONTH', b.date) = :month " +
		       "AND FUNCTION('YEAR', b.date) = :year " +
		       "GROUP BY b.servicename")
		List<Object[]> getMonthlyReport(@Param("month") int month, @Param("year") int year);
//	@Query("SELECT b.servicename,COUNT(b),SUM(b.price)" +
//			"FROM BookingEntity b" +
//			"WHERE FUNCTION('YEAR', b.date)= :year" +
//			"GROUP BY b.servicename")
//	@Query("SELECT b.servicename, COUNT(b), SUM(b.price) " +
//		       "FROM BookingEntity b " +
//		       "WHERE FUNCTION('YEAR', b.date) = :year " +
//		       "GROUP BY b.servicename")
//		List<Object[]> getServiceReportByYear(@Param("year") int year);
		@Query("SELECT b.servicename, COUNT(b), SUM(b.price) FROM BookingEntity b WHERE YEAR(b.date) = :year GROUP BY b.servicename")
		List<Object[]> getYearlyCatReport(@Param("year") int year);

		List<BookingEntity> findByDateOrderByState(LocalDate date);
}
