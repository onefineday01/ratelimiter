package com.onefineday.ratelimiter.repositories;

import com.onefineday.ratelimiter.models.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Page<Token> findAllByUserId(long id, Pageable pageable);
}
