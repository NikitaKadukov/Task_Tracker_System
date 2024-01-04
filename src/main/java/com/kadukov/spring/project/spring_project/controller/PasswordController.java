package com.kadukov.spring.project.spring_project.controller;

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
public class PasswordController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/editPassword")
    public String editPassword(Model model){
        User user = (User)httpSession.getAttribute("username");
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        if(user==null){
            return "redirect:/";
        }
        User curUser = new User();
        model.addAttribute("curUser", curUser);
        return "edit_password";
    }
    @RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
    public String checkPassword(@Valid @ModelAttribute("curUser") User curUser, BindingResult bindingResult, @RequestParam String newPassword, Model model){
        User user = (User)httpSession.getAttribute("username");
        if(user==null){
            return "redirect:/";
        }
        System.out.println(newPassword + " " + curUser.getPassword());
        if(curUser.getPassword().equals(user.getPassword()) && newPassword.length()>=6){
            if(bindingResult.hasErrors()){
                return "edit_password";
            }
            else {
                model.addAttribute("errorPassword", false);
                user.setPassword(newPassword);
                userService.saveUser(user);
                return "redirect:/madeChanges";
            }
        }
        else{
            model.addAttribute("errorPassword", true);
            return "edit_password";
        }
    }

    @RequestMapping("/madeChanges")
    public String madeChanges(Model model){
        User user = (User)httpSession.getAttribute("username");
        model.addAttribute("darkDesign", httpSession.getAttribute("darkDesign"));
        if(user==null){
            return "redirect:/";
        }
        return "made_changes";
    }


}
