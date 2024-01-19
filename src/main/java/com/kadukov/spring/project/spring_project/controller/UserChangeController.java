package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.TaskService;
import com.kadukov.spring.project.spring_project.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserChangeController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/changeRole/{name}")
    public String changeRole(@PathVariable String name){
        User user = (User)httpSession.getAttribute("user");
        if(!user.getRole().equals("admin")){
            return "redirect:/";
        }
        userService.changeRole(name);
        return "redirect:/adminPage";
    }

    @RequestMapping("/blockUser/{name}")
    public String blockUser(@PathVariable String name){
        User user = (User)httpSession.getAttribute("user");
        if(!user.getRole().equals("admin") && !user.getRole().equals("subAdmin")){
            return "redirect:/";
        }
        userService.blockUser(name);
        return "redirect:/adminPage";
    }

}
