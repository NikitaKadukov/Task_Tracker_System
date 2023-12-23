package com.kadukov.spring.project.spring_project.controller;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
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
        String lastSorted = (String) httpSession.getAttribute("sortListLast");
        String curSorted = (String)httpSession.getAttribute("sortList");

        boolean chetSort = (boolean) httpSession.getAttribute("chetSort");

        if (curSorted.equals("priority")) {
            tasks.sort(Task.priorityComparator);
        }
        if (curSorted.equals("title")) {
            tasks.sort(Task.titleComparator);
        }
        if (curSorted.equals("description")) {
            tasks.sort(Task.descriptionComparator);
        }
        if (curSorted.equals("deadline")) {
            tasks.sort(Task.deadlineComparator);
        }

        if(curSorted.equals(lastSorted) && !curSorted.equals("default")){
            chetSort = !chetSort;
            if(chetSort) {
                System.out.println(lastSorted + "  " + curSorted);
                Collections.reverse(tasks);
            }
        }
        else{
            chetSort = false;
        }
        httpSession.removeAttribute("sortListLast");
        httpSession.setAttribute("sortListLast", (String)httpSession.getAttribute("sortList"));
        httpSession.removeAttribute("chetSort");
        httpSession.setAttribute("chetSort", chetSort);

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
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        System.out.println(task.getDeadline());
        if(bindingResult.hasErrors()){
            return "showTask";
        }
        else {
            User user = (User) httpSession.getAttribute("username");
            task.setOwner(user.getUsername());
            taskService.saveTask(task);
            return "redirect:/task-tracker/";
        }
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

    @RequestMapping("/markTask/{id}")
    public String markTask(@PathVariable int id){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        taskService.markTask(id);
        return "redirect:/task-tracker/";
    }
}
