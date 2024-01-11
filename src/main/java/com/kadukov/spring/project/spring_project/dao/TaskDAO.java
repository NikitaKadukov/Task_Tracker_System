package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.Task;

import java.util.List;

public interface TaskDAO {
    public List<Task> getTasks(boolean root);

    public List<Task> getTasks(boolean root, String username);
    public void saveTask(Task task);

    public void markTask(int id);

    public Task getTask(int id);

    public void deleteTask(int id);

    public void deleteTask(int id, String username);

}
