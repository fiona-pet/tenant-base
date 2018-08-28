package com.fionapet.tenant.repository;

import java.util.Optional;

import com.fionapet.tenant.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
	boolean existsByEmail(String email);
	
	boolean existsByUsername(String username); 
	
}
