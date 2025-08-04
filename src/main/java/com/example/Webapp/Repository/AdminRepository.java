package com.example.Webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Webapp.Entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity,String> {
	AdminEntity findByPhonenumberAndPassword(String phonenumber,String password);
}
