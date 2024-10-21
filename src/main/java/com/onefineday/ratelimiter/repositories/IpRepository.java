package com.onefineday.ratelimiter.repositories;

import com.onefineday.ratelimiter.models.Ip;
import com.onefineday.ratelimiter.models.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<Ip, Long> {

    Page<Ip> findAllByTokenId(long id, Pageable pageable);

}
