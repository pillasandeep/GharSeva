package com.example.Webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Webapp.Entity.CustomerEntity;
public interface CustomerRepository extends JpaRepository<CustomerEntity,String> {
	CustomerEntity findByPhonenumberAndPassword(String phonenumber,String password);
	CustomerEntity findByPhonenumber(String phonenumber);
	//String findByPhonenumber(String phonenumber);
}
