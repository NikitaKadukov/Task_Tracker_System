package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.exception_handling.NoSuchTaskException;
import com.kadukov.spring.project.spring_project.exception_handling.NoSuchUserException;
import com.kadukov.spring.project.spring_project.service.LoginData;
import com.kadukov.spring.project.spring_project.service.TaskRequest;
import com.kadukov.spring.project.spring_project.service.TaskService;
import com.kadukov.spring.project.spring_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RESTController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @PostMapping("/tasks/{username}")
    public List<Task> showAllTasks(@PathVariable String username, @RequestBody String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (userService.valid(user)) {
            List<Task> taskList = taskService.getTasks(true, username);
            return taskList;
        }
        else{
            throw new NoSuchUserException("Нет такого пользователя");
        }
    }

    @PostMapping("/subtasks/{username}")
    public List<Task> showAllSubtasks(@PathVariable String username, @RequestBody String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (userService.valid(user)) {
            List<Task> taskList = taskService.getTasks(false, username);
            return taskList;
        }
        else{
            throw new NoSuchUserException("Нет такого пользователя");
        }
    }

    @PostMapping("/task/{id}")
    public Task showTask(@PathVariable int id, @RequestBody LoginData loginData){
        User user = new User();
        user.setUsername(loginData.getUsername());
        user.setPassword(loginData.getPassword());
        if (userService.valid(user)) {
            Task task = taskService.getTask(id);
            if(task==null){
                throw new NoSuchTaskException("Нет такого задания");
            }
            return task;
        }
        else{
            throw new NoSuchUserException("Нет такого пользователя");
        }
    }

    @PostMapping("/addTask")
    public void addTask(@RequestBody TaskRequest taskRequest){
        User user = new User();
        String username = taskRequest.getLoginData().getUsername();
        user.setUsername(username);
        user.setPassword(taskRequest.getLoginData().getPassword());
        Task task = taskRequest.getTask();
        task.setIs_done(false);
        task.setOwner(username);
        task.setCategory("none");

        if(task.getRef_task()!=0){
            if(!taskService.existTaskById(task.getRef_task(), true, username)){
                throw new NoSuchTaskException("Нет доступа к этому заданию");
            }
        }

        if (userService.valid(user)) {
                taskService.saveTask(task);
        }
        else{
            throw new NoSuchUserException("Нет такого пользователя");
        }
    }

    @PutMapping("/changeTask")
    public void changeTask(@RequestBody TaskRequest taskRequest){
        User user = new User();
        String username = taskRequest.getLoginData().getUsername();
        user.setUsername(username);
        user.setPassword(taskRequest.getLoginData().getPassword());
        Task task = taskRequest.getTask();
        task.setIs_done(false);
        task.setOwner(username);
        task.setCategory("none");

        if (!userService.valid(user)){
            throw new NoSuchUserException("Нет такого пользователя");
        }

        if(!taskService.existTaskById(task.getId(), true, username) &&
                !taskService.existTaskById(task.getId(), false, username)){
            throw new NoSuchTaskException("Нет такого задания");
        }

        if(task.getRef_task()!=0 && !taskService.existTaskById(task.getRef_task(), true, username)){
            throw new NoSuchTaskException("Нет доступа к этому заданию");
        }

        taskService.saveTask(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable int id, @RequestBody LoginData loginData){
        User user = new User();
        String username = loginData.getUsername();
        user.setUsername(username);
        user.setPassword(loginData.getPassword());

        if (!userService.valid(user)) {
            throw new NoSuchUserException("Нет такого пользователя");
        }

        if(!taskService.existTaskById(id, true, username) &&
                !taskService.existTaskById(id, false, username)){
            throw new NoSuchTaskException("Нет такого задания или доступа к нему");
        }

        taskService.deleteTask(id, username);
    }

    @PostMapping("/markTask/{id}")
    public void markTask(@PathVariable int id, @RequestBody LoginData loginData){
        User user = new User();
        String username = loginData.getUsername();
        user.setUsername(username);
        user.setPassword(loginData.getPassword());

        if (!userService.valid(user)) {
            throw new NoSuchUserException("Нет такого пользователя");
        }

        if(!taskService.existTaskById(id, true, username) &&
                !taskService.existTaskById(id, false, username)){
            throw new NoSuchTaskException("Нет такого задания или доступа к нему");
        }

        taskService.markTask(id);
    }

}
