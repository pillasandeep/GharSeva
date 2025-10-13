package com.example.Webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Webapp.Entity.TechBookingEntity;

public interface TechBookingRepository extends JpaRepository<TechBookingEntity,Long> {

}
