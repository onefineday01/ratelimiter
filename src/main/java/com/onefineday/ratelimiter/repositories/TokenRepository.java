package com.onefineday.ratelimiter.repositories;

import com.onefineday.ratelimiter.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
