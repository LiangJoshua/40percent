package com.forty_percent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.forty_percent.entity.ApplicationUser;

@Repository("postgres")
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>{

	@Query("select u from ApplicationUser u where u.username = :username")
	Optional<ApplicationUser> findByUsername(@Param("username") String username);

	@Query("select u from ApplicationUser u where u.email = :email")
	Optional<ApplicationUser> findByEmail(@Param("email") String email);
}
