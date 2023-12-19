package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sorting")
public class SortController {
    @Autowired
    TaskService taskService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/sortTasks/{sort}")
    public String sortList(@PathVariable String sort){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        httpSession.removeAttribute("sortList");
        httpSession.setAttribute("sortList", sort);
        return "redirect:/task-tracker/";
    }
}
