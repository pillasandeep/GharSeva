package com.example.Webapp.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Webapp.Entity.FeedbackEntity;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Long>{

}
