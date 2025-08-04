package com.example.Webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.Webapp.Entity.CartDetailsEntity;

public interface CartRepository extends JpaRepository<CartDetailsEntity,Long>{
	void deleteByPhonenumberAndServicenameAndPrice(String phonenumber,String servicename,int price);
	List<CartDetailsEntity> findByPhonenumber(String phonenumber);
	boolean existsByPhonenumberAndServicenameAndPrice(String phonenumber, String servicename, int price);

}
