package com.kadukov.spring.project.spring_project.controller;

import com.kadukov.spring.project.spring_project.entity.Task;
import com.kadukov.spring.project.spring_project.entity.User;
import com.kadukov.spring.project.spring_project.service.MailSender;
import com.kadukov.spring.project.spring_project.service.TaskService;
import com.kadukov.spring.project.spring_project.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    UserService userService;

    @Autowired
    private MailSender mailSender;
    @RequestMapping("/")
    public String menu_page(){

        return "menu";
    }

    @RequestMapping("/registry")
    public String registry_page(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registry";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registry";
        }
        else {
            user.setEnabled(true);
            user.setRole("user");
            userService.saveUser(user);
            return "redirect:/";
        }
    }

    @RequestMapping("/forgotPassword")
    public String forgotPassword(){
        mailSender.send("nikitaets2016@gmail.com", "Hi", "Darova");
        return "forgotPasswordView";
    }

    @RequestMapping("/signIN")
    public String signIN(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signIN";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("task") User user, Model model, HttpSession session){
        if(userService.valid(user)){
            User user2 = userService.getUser(user.getUsername());
            session.setAttribute("username", user2);
            session.setAttribute("sortList", "default");
            session.setAttribute("sortListLast", "default");
            session.setAttribute("darkDesign", false);
            session.setAttribute("chetSort", false);
            return "redirect:/task-tracker/";
        }
        else{
            return "redirect:/";
        }
    }

    @RequestMapping("/about")
    public String about(HttpSession httpSession, Model model){
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        if(httpSession.getAttribute("username")==null){
            return "redirect:/";
        }
        return "about_page";
    }

}
