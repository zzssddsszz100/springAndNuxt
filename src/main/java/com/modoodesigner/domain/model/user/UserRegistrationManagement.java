package com.modoodesigner.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationManagement {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User register(String username, String emailAddress, String password) throws UserRegistrationException {
        if (repository.existsByUsername(username)) {
            throw new UsernameExistsException();
        }
        if (repository.existsByEmailAddress(emailAddress.toLowerCase())) {
            throw new EmailAddressExistsException();
        }

        String encryptedPassword = passwordEncoder.encode(password);
        User newUser = User.create(username,emailAddress,encryptedPassword);
        repository.save(newUser);

        return newUser;

    }

}
