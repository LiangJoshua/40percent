package com.forty_percent.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forty_percent.config.UserRole;
import com.forty_percent.entity.ApplicationUser;
import com.forty_percent.exception.UserCreationException;
import com.forty_percent.repository.ApplicationUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found.", username)));
    }

    public ApplicationUser signupUser(
            String firstName,
            String lastName,
            String username,
            String email,
            String password,
            List<UserRole> roles) throws UserCreationException {
        if (applicationUserRepository.findByUsername(username).isPresent()) {
            throw new UserCreationException(String.format("User with username %s already exists", username));
        }

        if (applicationUserRepository.findByEmail(email).isPresent()) {
            throw new UserCreationException(String.format("User with email %s already exists", username));
        }

        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(username);
        applicationUser.setFirstName(firstName);
        applicationUser.setLastName(lastName);
        applicationUser.setEmail(email);
        applicationUser.setPassword(passwordEncoder.encode(password));
        applicationUser.setRoles(roles);
        applicationUser.setAccountNonExpired(true);
        applicationUser.setAccountNonLocked(true);
        applicationUser.setCredentialsNonExpired(true);
        applicationUser.setEnabled(false);
        applicationUserRepository.save(applicationUser);

        return applicationUser;
    }

    public void enableUser(ApplicationUser applicationUser) {
        applicationUser.setEnabled(true);
        applicationUserRepository.save(applicationUser);
    }
}
