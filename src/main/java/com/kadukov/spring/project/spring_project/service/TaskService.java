package com.kadukov.spring.project.spring_project.service;




import com.kadukov.spring.project.spring_project.entity.Task;

import java.util.List;

public interface TaskService {
    public List<Task> getTasks(boolean root);

    public void saveTask(Task task);

    public void markTask(int id);

    public Task getTask(int id);

    public void deleteTask(int id);

    public int numDoneTask(boolean root);
}
