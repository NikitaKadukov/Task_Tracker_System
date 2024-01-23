package com.kadukov.spring.project.spring_project.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column
    @Pattern(regexp = "\\w{3,}", message = "Минимум 3 символа (латинские буквы, цифры) без пробелов")
    @Size(max = 15, message = "Не больше 15 символов")
    private String username;
    @Column
    @Pattern(regexp = "\\w{6,}", message = "Минимум 6 символов (латинские буквы, цифры) без пробелов")
    @Size(max = 15, message = "Не больше 15 символов")
    private String password;
    @Column
    private boolean enabled;
    @Column
    @Pattern(regexp = "\\w+@\\w+\\.\\w+", message = "Введите корректную почту")
    @Size(max = 25, message = "Не больше 25 символов")
    private String email;
    @Column
    private String role;

    public User() {
    }

    public User(String username, String password, boolean enabled, String email, String role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
