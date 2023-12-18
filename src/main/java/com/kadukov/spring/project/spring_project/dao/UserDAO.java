package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.User;

public interface UserDAO {
    public void saveUser(User user);

    public boolean valid(User user);
}
