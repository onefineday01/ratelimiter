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
@Table(name = "ip", uniqueConstraints = {@UniqueConstraint(columnNames = {"token_id", "ip"})})  // Unique constraint if need to make joint index on 2 or more columns
public class Ip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String Ip;

    @ManyToOne
    @JoinColumn(name = "token_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Allow User to be written, but not read
    private Token token;

    @Column(nullable = false)
    private IpStatus status;

    @Column(name = "blocked_at")
    private LocalDateTime blockedAt;

    @CreatedDate // Automatically populated when the entity is created
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updated when the entity is modified
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
}
