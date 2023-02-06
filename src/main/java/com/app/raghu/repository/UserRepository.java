package com.app.raghu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.raghu.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
}
