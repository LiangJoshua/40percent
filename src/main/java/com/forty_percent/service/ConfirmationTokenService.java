package com.forty_percent.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.forty_percent.entity.ApplicationUser;
import com.forty_percent.entity.ConfirmationToken;
import com.forty_percent.exception.UserRegistrationException;
import com.forty_percent.repository.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public String saveConfirmationToken(ApplicationUser applicationUser) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(Clock.systemUTC()),
                LocalDateTime.now(Clock.systemUTC()).plusMinutes(15),
                applicationUser);
        confirmationTokenRepository.save(confirmationToken);
        return token;
    }

    public ConfirmationToken confirmToken(String token) throws UserRegistrationException {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new UserRegistrationException("Confirmation token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new UserRegistrationException("Email already confirmed");
        }

        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        if (confirmationToken.getExpiresAt().isBefore(now)) {
            throw new UserRegistrationException("Token expired");
        }

        confirmationTokenRepository.updateConfirmedAt(token, now);
        return confirmationToken;
    }
}
