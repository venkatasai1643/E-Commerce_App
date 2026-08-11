package com.e_commerce.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_commerce.application.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}