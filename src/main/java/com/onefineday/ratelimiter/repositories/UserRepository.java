package com.onefineday.ratelimiter.repositories;

import com.onefineday.ratelimiter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
