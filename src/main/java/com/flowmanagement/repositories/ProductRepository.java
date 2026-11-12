package com.flowmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowmanagement.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	

}
