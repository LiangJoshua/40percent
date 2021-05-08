package com.forty_percent.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forty_percent.config.UserRole;
import com.forty_percent.entity.ApplicationUser;
import com.forty_percent.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService implements UserDetailsService {

	private final ApplicationUserRepository applicationUserRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationUserService(
			@Qualifier("postgres") ApplicationUserRepository applicationUserRepository,
			PasswordEncoder passwordEncoder){
		this.applicationUserRepository = applicationUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		return applicationUserRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found.", username)));
	}

	public void register(String username, String password) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername(username);
		applicationUser.setPassword(passwordEncoder.encode(password));
		applicationUser.setRoles(Arrays.asList(UserRole.USER));
		applicationUser.setAccountNonExpired(true);
		applicationUser.setAccountNonLocked(true);
		applicationUser.setCredentialsNonExpired(true);
		applicationUser.setEnabled(true);
		applicationUserRepository.save(applicationUser);
	}
}
