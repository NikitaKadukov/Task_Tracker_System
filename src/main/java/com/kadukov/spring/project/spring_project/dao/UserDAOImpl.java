package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.Task;
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
    public boolean valid(User user) {
        List<User> taskUsers = entityManager.createQuery("from User").getResultList();
        for(User user1: taskUsers){
            if(user1.getUsername().equals(user.getUsername())){
                if(user1.getPassword().equals(user.getPassword()) && user1.isEnabled()){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers(String role) {
        Query query;
        if(role.equals("admin")){
            query = entityManager.createQuery("from User where role = 'user' OR role = 'subAdmin'");
        }
        else if(role.equals("subAdmin")){
            query = entityManager.createQuery("from User where role = 'user'");
        }
        else query = null;

        List<User> userList = query.getResultList();
        return userList;
    }


    @Override
    public User whichEmail(String email){
        for(User user: getUsers()){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    private List<User> getUsers() {
        Query query;
        query = entityManager.createQuery("from User");
        List<User> userList = query.getResultList();
        return userList;
    }

}
