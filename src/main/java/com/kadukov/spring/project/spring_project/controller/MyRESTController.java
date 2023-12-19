package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task-tracker")
public class MyRESTController {

    @Autowired
    TaskService taskService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/")
    public String start_page(Model model){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        User user = (User) httpSession.getAttribute("username");
        model.addAttribute("username", user.getUsername());
        List<Task> tasks = taskService.getTasks();
        if(((String)httpSession.getAttribute("sortList")).equals("priority"))tasks.sort(Task.priorityComparator);
        if(((String)httpSession.getAttribute("sortList")).equals("title")) tasks.sort(Task.titleComparator);
        if(((String)httpSession.getAttribute("sortList")).equals("description")) tasks.sort(Task.descriptionComparator);
        if(((String)httpSession.getAttribute("sortList")).equals("deadline")) tasks.sort(Task.deadlineComparator);
        if(((String)httpSession.getAttribute("sortList")).equals("is_done")) tasks.sort(Task.statusComparator);
        model.addAttribute("tasks", tasks);
        return "start";
    }

    @RequestMapping("/addNewTask")
    public String addNewTask(Model model){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        Task task = new Task();
        task.setId(0); task.setIs_done(false);
        model.addAttribute("task", task);
        return "showTask";
    }

    @RequestMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        System.out.println(task);
        User user = (User) httpSession.getAttribute("username");
        task.setOwner(user.getUsername());
        taskService.saveTask(task);
        return "redirect:/task-tracker/";
    }

    @RequestMapping("/updateTask/{id}")
    public String updateInfo(@PathVariable int id, Model model){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        Task task = taskService.getTask(id);
        model.addAttribute("task", task);
        return "showTask";
    }

    @RequestMapping("/deleteTask/{id}")
    public String deleteInfo(@PathVariable int id){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        taskService.deleteTask(id);
        return "redirect:/task-tracker/";
    }

    @RequestMapping("/logout")
    public String exit(){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        httpSession.removeAttribute("username");
        return "redirect:/";
    }
}
