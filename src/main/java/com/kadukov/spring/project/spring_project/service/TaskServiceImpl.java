package com.kadukov.spring.project.spring_project.service;

import com.kadukov.spring.project.spring_project.dao.TaskDAO;
import com.kadukov.spring.project.spring_project.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;

    @Transactional
    @Override
    public List<Task> getTasks(boolean root) {
        return taskDAO.getTasks(root);
    }

    @Transactional
    @Override
    public List<Task> getTasks(boolean root, String username) {
        return taskDAO.getTasks(root, username);
    }

    @Transactional
    @Override
    public void saveTask(Task task) {
        taskDAO.saveTask(task);
    }


    @Transactional
    @Override
    public Task getTask(int id) {
        return taskDAO.getTask(id);
    }

    @Transactional
    @Override
    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }

    @Transactional
    @Override
    public void deleteTask(int id, String username) {
        taskDAO.deleteTask(id, username);
    }

    @Transactional
    @Override
    public int numDoneTask(boolean root) {
        List<Task> taskList = getTasks(root);
        int cnt = 0;
        for(Task task: taskList){
            if(task.isIs_done()) cnt++;
        }
        return cnt;
    }

    @Transactional
    @Override
    public void markTask(int id) {
        taskDAO.markTask(id);
    }

    @Transactional
    @Override
    public boolean existTaskById(int id, boolean root, String username){
        for(Task taskcur: taskDAO.getTasks(root, username)){
            if(taskcur.getId()==id){
                return true;
            }
        }
        return false;
    }
}
