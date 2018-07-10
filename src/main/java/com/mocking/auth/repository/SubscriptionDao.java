package com.mocking.auth.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mocking.auth.model.Subscription;

public interface SubscriptionDao extends JpaRepository<Subscription, Long>{
	
}
