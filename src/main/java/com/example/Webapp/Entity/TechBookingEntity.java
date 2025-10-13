package com.example.Webapp.Entity;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "techbooking")
public class TechBookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long techid; // Primary key for this table

    @ManyToOne
    @JoinColumn(name = "technician_id", referencedColumnName = "id")
    private TechnicianEntity technician;

    @OneToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private BookingEntity booking;

    // ✅ New field to track service status
    @Column(length = 50)
    private String status; // e.g., "Pending", "Completed", "In Progress"
    private LocalDate date;
    public TechBookingEntity() {
        super();
    }

    public TechBookingEntity(TechnicianEntity technician, BookingEntity booking, String status,LocalDate date) {
        super();
        this.technician = technician;
        this.booking = booking;
        this.status = status;
        this.date=date;
    }

    public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	// Getters and Setters
    public long getTechid() {
        return techid;
    }

    public void setTechid(long techid) {
        this.techid = techid;
    }

    public TechnicianEntity getTechnician() {
        return technician;
    }

    public void setTechnician(TechnicianEntity technician) {
        this.technician = technician;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
