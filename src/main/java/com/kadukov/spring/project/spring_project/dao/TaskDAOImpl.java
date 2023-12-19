package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    HttpSession httpSession;

    @Override
    public List<Task> getTasks() {
        User user = (User)httpSession.getAttribute("username");
        String username = user.getUsername();
        Query query = entityManager.createQuery("from Task where owner = :user");
        query.setParameter("user", username);
        List<Task> taskList = query.getResultList();
        return taskList;
    }

    @Override
    public void saveTask(Task task) {
        task.setId(entityManager.merge(task).getId());
    }


    @Override
    public Task getTask(int id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public void deleteTask(int id) {
        Query query = entityManager.createQuery("delete from Task where id = :TaskId");
        query.setParameter("TaskId", id);
        query.executeUpdate();
    }
}
