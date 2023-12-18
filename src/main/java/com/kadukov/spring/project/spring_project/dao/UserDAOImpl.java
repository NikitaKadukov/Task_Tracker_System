package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManager entityManager;
    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public boolean valid(User user) {
        List<User> taskUsers = entityManager.createQuery("from User").getResultList();
        for(User user1: taskUsers){
            if(user1.getUsername().equals(user.getUsername())){
                if(user1.getPassword().equals(user.getPassword())){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
}
