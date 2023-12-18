package com.kadukov.spring.project.spring_project.service;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;

public interface UserService {
    public void saveUser(User user);

    public boolean valid(User user);
}
