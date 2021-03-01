package com.modoodesigner.domain.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmailAddress(String emailAddress);
}
