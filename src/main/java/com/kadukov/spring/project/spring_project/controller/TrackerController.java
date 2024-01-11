package com.kadukov.spring.project.spring_project.controller;
import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.TaskService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/task-tracker")
public class TrackerController {

    @Autowired
    TaskService taskService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/")
    public String start_page(Model model){

        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("username", user.getUsername());
        List<Task> tasks = taskService.getTasks(true);
        List<Task> subTasks = taskService.getTasks(false);

        String curSorting = (String)httpSession.getAttribute("sortList");

        boolean paritySort = (boolean) httpSession.getAttribute("paritySort");

        if (curSorting.equals("priority")) {
            tasks.sort(Task.priorityComparator);
        }
        if (curSorting.equals("title")) {
            tasks.sort(Task.titleComparator);
        }
        if (curSorting.equals("description")) {
            tasks.sort(Task.descriptionComparator);
        }
        if (curSorting.equals("deadline")) {
            tasks.sort(Task.deadlineComparator);
        }

        if(paritySort){
            Collections.reverse(tasks);
        }

        model.addAttribute("tasks", tasks);
        model.addAttribute("subTasks", subTasks);
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "start";
    }

    @RequestMapping("/addNewTask")
    public String addNewTask(Model model){
        Task task = new Task();
        task.setId(0); task.setIs_done(false); task.setCategory("default"); task.setRef_task(0);
        model.addAttribute("task", task);
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "showTask";
    }

    @RequestMapping("/saveTask")
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "showTask";
        }
        else {
            User user = (User) httpSession.getAttribute("user");
            task.setOwner(user.getUsername());
            taskService.saveTask(task);
            return "redirect:/task-tracker/";
        }
    }

    @RequestMapping("/updateTask/{id}")
    public String updateInfo(@PathVariable int id, Model model){
        Task task = taskService.getTask(id);
        model.addAttribute("task", task);
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "showTask";
    }

    @RequestMapping("/deleteTask/{id}")
    public String deleteInfo(@PathVariable int id){
        taskService.deleteTask(id);
        return "redirect:/task-tracker/";
    }

    @RequestMapping("/logout")
    public String exit(){
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("sortList");
        httpSession.removeAttribute("sortListLast");
        httpSession.removeAttribute("darkDesign");
        httpSession.removeAttribute("paritySort");
        return "redirect:/";
    }

    @RequestMapping("/markTask/{id}")
    public String markTask(@PathVariable int id){
        taskService.markTask(id);
        return "redirect:/task-tracker/";
    }

    @RequestMapping("/addRefTask/{id}")
    public String addRefTask(@PathVariable int id, Model model){
        Task task = new Task();
        task.setId(0); task.setIs_done(false); task.setCategory("none"); task.setRef_task(id);task.setPriority(0);
        model.addAttribute("task", task);
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "showTask";
    }
}
