package com.kadukov.spring.project.spring_project.service;

import com.kadukov.spring.project.spring_project.dao.TaskDAO;
import com.kadukov.spring.project.spring_project.dao.UserDAO;
import com.kadukov.spring.project.spring_project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Transactional
    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public boolean valid(User user) {
        return userDAO.valid(user);
    }
}
