package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.TaskService;
import com.kadukov.spring.project.spring_project.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InfoController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/profile")
    public String profile_page(Model model){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        model.addAttribute("numTask", taskService.getTasks(true).size());
        model.addAttribute("numDoneTask", taskService.numDoneTask(true));
        model.addAttribute("numSubTask", taskService.getTasks(false).size());
        model.addAttribute("numDoneSubTask", taskService.numDoneTask(false));
        model.addAttribute("user", (User)httpSession.getAttribute("username"));
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "profile";
    }

    @RequestMapping("/darkMode")
    public String darkMode(Model model){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        httpSession.setAttribute("darkDesign", !(boolean)(httpSession.getAttribute("darkDesign")));
        return "redirect:/profile";
    }

    @RequestMapping("/adminPage")
    public String adminPage(Model model){
        User user = (User)httpSession.getAttribute("username");
        if(user==null){
            return "redirect:/";
        }
        if(!user.getRole().equals("admin") && !user.getRole().equals("subAdmin")){
            return "redirect:/";
        }
        model.addAttribute("users", userService.getAllUsers(((User)httpSession.getAttribute("username")).getRole()));
        model.addAttribute("curUser", (User)httpSession.getAttribute("username"));
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "admin_page";
    }



}
