package com.kadukov.spring.project.spring_project.service;

import com.kadukov.spring.project.spring_project.entity.Task;

public class TaskRequest {
    private LoginData loginData;
    private Task task;

    public TaskRequest() {
    }

    public TaskRequest(LoginData loginData, Task task) {
        this.loginData = loginData;
        this.task = task;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
