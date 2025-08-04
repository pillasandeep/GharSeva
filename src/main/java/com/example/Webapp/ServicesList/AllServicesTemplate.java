package com.example.Webapp.ServicesList;

public class AllServicesTemplate {

	private String servicename;
	private int price;
	private String image;
	public AllServicesTemplate()
	{
		
	}
	public AllServicesTemplate(String servicename, int price,String image) {
		super();
		this.servicename = servicename;
		this.price = price;
		this.image=image;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
}
