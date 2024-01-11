package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
    public User getUser(String name) {
        return entityManager.find(User.class, name);
    }

    @Override
    public List<User> getAllUsers(String role) {
        Query query;
        if(role.equals("all")){
            query = entityManager.createQuery("from User");
        }
        else if(role.equals("admin")){
            query = entityManager.createQuery("from User where role = 'user' OR role = 'subAdmin'");
        }
        else if(role.equals("subAdmin")){
            query = entityManager.createQuery("from User where role = 'user'");
        }
        else query = null;

        List<User> userList = query.getResultList();
        return userList;
    }

}
