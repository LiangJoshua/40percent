package com.forty_percent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.forty_percent.entity.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long>{

	@Query("select u from ApplicationUser u where u.username = :username")
	ApplicationUser findByUsername(@Param("username") String username);

	@Query("select u from ApplicationUser u where u.email = ?1")
	ApplicationUser findByEmail(String email);
}
