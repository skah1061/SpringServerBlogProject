package com.sparta.blog.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    long id;
    @Column(name="password", nullable = false)
    String password;
    @Column(name="username", nullable = false, unique = true)
    String username;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
