package com.example.Webapp.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin")
public class AdminEntity {

	@Id
	private String phonenumber;
	@Column
	private String password;
	
	public AdminEntity()
	{
		
	}
	public AdminEntity(String phonenumber,String password)
	{
		this.phonenumber=phonenumber;
		this.password=password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
