package com.flowmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowmanagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	

}
