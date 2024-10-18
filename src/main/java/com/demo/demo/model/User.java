package com.demo.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "USER")
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
    private Date created_at;

    @LastModifiedDate
    @Column()
    private Date updatedAt;

    @Column()
    private Date deleted_at;

    @PrePersist
    public void prePersist() {
        if (this.created_at == null) {
            this.created_at = new Date();
        }
    }
}