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
	Optional<BookingEntity> findById(Long id);
//	List<BookingEntity> findByDate(LocalDate date);
//		@Query("SELECT b , t.name "+
//				"FROM BookingEntity b "+
//				"JOIN TechBookingEntity tt ON b.id=tt.booking.id and b.state='Assigned' and b.date = :date"+
//				"JOIN TechnicianEntity t ON tt.technician.id=t.id ")
//		List<Object[]> findBookingsByDateAndState(LocalDate date,String state);
	@Query(value = """
		    SELECT 
		        b.id, 
		        b.servicename, 
		        b.name, 
		        b.date, 
		        tt.name AS technician_name
		    FROM bookings b
		    JOIN techbooking t ON b.id = t.booking_id
		    JOIN technician tt ON t.technician_id = tt.id
		    WHERE b.state = :state
		      AND b.date = :date
		""", nativeQuery = true)
		List<Object[]> getAssignedBookings(
		        @Param("state") String state,
		        @Param("date") LocalDate date
		);

	    @Query("SELECT b, t.name FROM BookingEntity b JOIN b.techBooking tb JOIN tb.technician t WHERE b.date = :date")
	    List<Object[]> findBookingsWithTechnician(@Param("date") LocalDate date);
	    @Query("SELECT b, t.name " +
	    	       "FROM BookingEntity b " +
	    	       "JOIN TechBookingEntity tb ON b.id = tb.booking.id " +
	    	       "JOIN TechnicianEntity t ON tb.technician.id = t.id " +
	    	       "WHERE b.date = :date " +
	    	       "AND (:phone IS NULL OR b.phonenumber = :phone)")
	    	List<Object[]> findBookingsByDateAndPhone(
	    	    @Param("date") LocalDate date,
	    	    @Param("phone") String phone
	    	);
	BookingEntity findById(long id);
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
