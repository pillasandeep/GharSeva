package com.example.Webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Webapp.Entity.TechnicianEntity;
public interface TechnicianRepository extends JpaRepository<TechnicianEntity,Long>{
		TechnicianEntity findByPhonenumberAndPassword(String phonenumber,String password);
		
}
