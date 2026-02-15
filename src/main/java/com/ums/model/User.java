package com.ums.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String googleSub; // Google Subject ID

    private String photoUrl;

    public enum Role {
        USER, ADMIN
    }
}
