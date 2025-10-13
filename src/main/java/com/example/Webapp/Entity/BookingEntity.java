package com.example.Webapp.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bookings")
public class BookingEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String servicename;

	private String name;

	private String address;
	private String landmark;
	private String phonenumber;
	private String sphonenumber;
	private LocalDate date;
	private LocalTime time;
	private int price;
	private String state;
	@OneToOne(mappedBy = "booking")
	private TechBookingEntity techBooking;
	public TechBookingEntity getTechnician() { 
		return techBooking; 
		}
	public void setTechnician(TechBookingEntity technician) { 
		this.techBooking = technician; 
		}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public BookingEntity() {
		super();
	}
	public BookingEntity(String servicename, String name, String address, String landmark, String phonenumber,
			String sphonenumber,int price,String state) {
		super();
		this.servicename = servicename;
		this.name = name;
		
		this.address = address;
		this.landmark = landmark;
		this.phonenumber = phonenumber;
		this.sphonenumber = sphonenumber;
		this.price=price;
		this.state=state;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getSphonenumber() {
		return sphonenumber;
	}
	public void setSphonenumber(String sphonenumber) {
		this.sphonenumber = sphonenumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
	
	
}
