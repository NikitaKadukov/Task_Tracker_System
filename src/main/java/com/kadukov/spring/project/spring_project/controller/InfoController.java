package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.TaskService;
import com.kadukov.spring.project.spring_project.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("numTask", taskService.getTasks(true).size());
        model.addAttribute("numDoneTask", taskService.numDoneTask(true));
        model.addAttribute("numSubTask", taskService.getTasks(false).size());
        model.addAttribute("numDoneSubTask", taskService.numDoneTask(false));
        model.addAttribute("user", (User)httpSession.getAttribute("user"));
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "profile";
    }

    @RequestMapping("/darkMode")
    public String darkMode(Model model){
        httpSession.setAttribute("darkDesign", !(boolean)(httpSession.getAttribute("darkDesign")));
        return "redirect:/profile";
    }

    @RequestMapping("/adminPage")
    public String adminPage(Model model){
        User user = (User)httpSession.getAttribute("user");
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        if(!user.getRole().equals("admin") && !user.getRole().equals("subAdmin")){
            return "redirect:/";
        }
        model.addAttribute("users", userService.getAllUsers(((User)httpSession.getAttribute("user")).getRole()));
        model.addAttribute("curUser", (User)httpSession.getAttribute("user"));
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "admin_page";
    }

    @RequestMapping("/about")
    public String about(HttpSession httpSession, Model model){
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        return "about_page";
    }

}
