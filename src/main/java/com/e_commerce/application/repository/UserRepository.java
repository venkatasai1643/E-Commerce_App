package com.e_commerce.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.e_commerce.application.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}