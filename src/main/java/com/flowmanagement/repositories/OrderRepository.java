package com.flowmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowmanagement.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	

}
