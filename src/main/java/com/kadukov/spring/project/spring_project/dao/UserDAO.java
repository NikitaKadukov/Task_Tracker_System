package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.User;

import java.util.List;

public interface UserDAO {
    public void saveUser(User user);

    public User getUser(String name);
    public boolean valid(User user);

    public List<User> getAllUsers(String role);

    public User whichEmail(String email);
}
