package com.onefineday.ratelimiter.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "token", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "user_id"})})  // Unique constraint if need to make joint index on 2 or more columns
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Allow User to be written, but not read
    private User user;

    @Column(nullable = false)
    private int request = 3; // Default 3 requests

    @Column(nullable = false)
    private int time = 60; // Default 3 requests in 60 seconds

    @Column(nullable = false, name = "block_time")
    private int blockTime = 3600; // // Default 3 requests in 60 seconds else block for 3600 seconds

    @Column(nullable = false)
    // @Enumerated(EnumType.STRING) // This stores the enum as a VARCHAR
    private TokenStatus status;

    @Column(name = "blocked_at")
    private LocalDateTime blockedAt;

    @CreatedDate // Automatically populated when the entity is created
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updated when the entity is modified
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
}
