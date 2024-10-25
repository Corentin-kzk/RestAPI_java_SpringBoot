package com.demo.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "USER_DATA") // The user keyword is reserved in later versions of H2.
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String firstname;
    @Column(length = 150)
    private String lastname;
    @Column(length = 150)
    private String password;
    @Column(length = 150)
    private String email;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    // Ne plus utiliser java.util.Date
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private LocalDateTime deleted_at;
    private boolean isDeleted;
    @PrePersist
    public void prePersist() {
        if (this.created_at == null) {
            this.created_at = LocalDateTime.now();
        }
    }
}