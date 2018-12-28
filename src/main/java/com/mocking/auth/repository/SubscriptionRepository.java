package com.mocking.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mocking.auth.model.Subscription;

/*@author feifei*/

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
