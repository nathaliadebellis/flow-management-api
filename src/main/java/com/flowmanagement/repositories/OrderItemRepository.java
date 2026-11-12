package com.flowmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowmanagement.entities.OrderItem;

import com.flowmanagement.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
	

}
