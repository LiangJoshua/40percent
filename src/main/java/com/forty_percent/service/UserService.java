package com.forty_percent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forty_percent.entity.ApplicationUser;
import com.forty_percent.model.User;
import com.forty_percent.repository.UserRepository;

@Service
public class UserService{

	@Autowired
	private UserRepository userRepository;

	public User registerUser(String username, String email, String password) {
		ApplicationUser user = new ApplicationUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPasswordEncoded(password);
		user = userRepository.save(user);
		return fromBean(user);
	}

	public User loadUserByUsername(String username) {
		return fromBean(userRepository.findByUsername(username));
	}

	public User loadUserByEmail(String email) {
		return fromBean(userRepository.findByEmail(email));
	}

	private User fromBean(ApplicationUser user){
		if (user == null) {
			return null;
		}
		return new User(user.getFirstName(), user.getLastName(), user.getUsername(),
				user.getEmail(), user.getPasswordEncoded());
	}
}
