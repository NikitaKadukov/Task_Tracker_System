package com.kadukov.spring.project.spring_project.service;

import com.kadukov.spring.project.spring_project.entity.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);

    public User getUser(String name);

    public List<User> getAllUsers(String role);

    public void changeRole(String name);

    public boolean valid(User user);

    public void blockUser(String name);

    public User whichEmail(String email);
}
